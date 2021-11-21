package cn.yangwanhao.springsecurity.controller;

import cn.yangwanhao.springsecurity.common.GlobalConstant;
import cn.yangwanhao.springsecurity.common.bean.Captcha;
import cn.yangwanhao.springsecurity.common.util.CaptchaUtil;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Gson gson;

    @RequestMapping(GlobalConstant.Uri.GENERATOR_CAPTCHA)
    public void showCheckCode(HttpServletRequest request,HttpServletResponse response){
        // 如果有验证码先删除刚刚的验证码
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            String oldKey = null;
            for (Cookie cookie : cookies) {
                if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                    oldKey = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
                }
            }
            if (StringUtils.isNotBlank(oldKey)) {
                stringRedisTemplate.delete(oldKey);
            }
        }
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Controller", "no-cache");
        response.setDateHeader("Expires", 0);
        //创建内存图形
        CaptchaUtil.CaptchaImg captchaImg = CaptchaUtil.getCaptcha();
        String uuid = UUID.randomUUID().toString();
        String key = GlobalConstant.Captcha.CAPTCHA_PREFIX + uuid;
        // 存入redis
        String captchaJson = gson.toJson(new Captcha(captchaImg.getCode(), GlobalConstant.Captcha.CAPTCHA_VALID_CHANCES));
        stringRedisTemplate.opsForValue().set(key, captchaJson, GlobalConstant.Captcha.CAPTCHA_VALID_TIME, TimeUnit.MINUTES);
        // 新建cookie
        Cookie cookie = new Cookie(GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME, uuid);
        // 设置时长
        cookie.setMaxAge(GlobalConstant.Captcha.CAPTCHA_VALID_TIME * 60);
        // 发送cookie
        response.addCookie(cookie);
        // 响应图片
        try(ServletOutputStream out = response.getOutputStream()) {
            captchaImg.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(GlobalConstant.Uri.LOGIN_SUCCESS)
    public String loginSuccess(HttpServletRequest request) {
        // 登陆成功后删除验证码
        String key = null;
        for (Cookie cookie : request.getCookies()) {
            if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                key = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
            }
        }
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.delete(key);
        }
        return "redirect:/page/index";
    }

}
