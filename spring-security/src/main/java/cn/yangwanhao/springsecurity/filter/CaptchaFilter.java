package cn.yangwanhao.springsecurity.filter;

import cn.yangwanhao.springsecurity.common.ErrorCodeEnum;
import cn.yangwanhao.springsecurity.common.GlobalConstant;
import cn.yangwanhao.springsecurity.common.bean.Captcha;
import cn.yangwanhao.springsecurity.common.util.SpringContextUtil;
import cn.yangwanhao.springsecurity.config.security.UserLoginAuthenticationFailureHandler;
import cn.yangwanhao.springsecurity.exception.CaptchaException;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码验证filter
 * @author 青鲤
 */
public class CaptchaFilter extends OncePerRequestFilter {

    /**
     * 由于filter先于spring容器被加载,所以此处无法直接注入bean,需要在下边手动实例化
     */
    private Gson gson;
    private StringRedisTemplate stringRedisTemplate;
    private UserLoginAuthenticationFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 校验验证码
            checkCaptcha(request);
        } catch (CaptchaException e) {
            // 实例化failureHandler()
            if (failureHandler == null) {
                failureHandler = (UserLoginAuthenticationFailureHandler) SpringContextUtil.getBean(UserLoginAuthenticationFailureHandler.class);
            }
            // 交给认证失败异常处理器
            failureHandler.onAuthenticationFailure(request, response, e);
            // 验证码校验失败就不用继续往下走了
            return;
        }
        // 一切都没有问题,继续向下执行
        filterChain.doFilter(request, response);
    }

    private void checkCaptcha(HttpServletRequest request) {
        // 只有URI是登录而且是POST方法时才拦截
        if (GlobalConstant.Uri.LOGIN.equals(request.getRequestURI()) && GlobalConstant.RequestMethod.POST.equals(request.getMethod())) {
            // 实例化stringRedisTemplate
            if (stringRedisTemplate == null) {
                stringRedisTemplate = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
            }
            // 实例化gson
            if (gson == null) {
                gson = (Gson) SpringContextUtil.getBean(Gson.class);
            }
            // 取出request中输入的验证码
            String httpCaptcha = request.getParameter("captcha");
            // 如果输入的验证码为空,抛出验证码为空异常
            if (httpCaptcha == null || "".equals(httpCaptcha)) {
                throw new CaptchaException(ErrorCodeEnum.U1001007.msg());
            }
            String key = null;
            // 取出cookie 以及 存放在cookie中的key
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                    key = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
                    break;
                }
            }
            // 如果key是null,说明验证码已经过期
            if (StringUtils.isBlank(key)) {
                throw new CaptchaException(ErrorCodeEnum.U1003001.msg());
            }
            // 拿到redis中的验证码
            Captcha captcha = gson.fromJson(stringRedisTemplate.opsForValue().get(key), Captcha.class);
            // 如果输入的验证码不为空但是位数不对,机会-1,抛出验证码错误
            if (httpCaptcha.length() != GlobalConstant.Captcha.CAPTCHA_SIZE) {
                chanceDecrement(key, captcha);
                throw new CaptchaException(ErrorCodeEnum.U1009002.msg());
            }
            // 如果从redis中拿到的captcha是null,说明验证码已经过期
            if (captcha == null || "".equals(captcha.getCode())) {
                throw new CaptchaException(ErrorCodeEnum.U1003001.msg());
            }
            // 如果输入的验证码和redis中拿到的验证码不相同
            if (!httpCaptcha.equals(captcha.getCode())) {
                // 机会-1
                chanceDecrement(key, captcha);
                // 抛出验证码错误异常
                throw new CaptchaException(ErrorCodeEnum.U1009002.msg());
            }
            // 验证码正确,机会也要-1
            chanceDecrement(key, captcha);
        }
    }

    private void chanceDecrement(String key, Captcha captcha) {
        // 机会-1
        captcha.setChances(captcha.getChances()-1);
        // 如果-1后的机会等于0
        if (captcha.getChances() <= 0) {
            // 删除redis中的验证码并抛出验证码过期异常
            stringRedisTemplate.delete(key);
            throw new CaptchaException(ErrorCodeEnum.U1003001.msg());
        }
        // 将redis中的验证码机会-1
        stringRedisTemplate.opsForValue().set(key, gson.toJson(captcha), 0);
    }
}
