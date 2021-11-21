package cn.yangwanhao.springsecurity.config.security;

import cn.yangwanhao.springsecurity.common.ErrorCodeEnum;
import cn.yangwanhao.springsecurity.common.bean.Message;
import cn.yangwanhao.springsecurity.exception.CaptchaException;
import com.google.gson.Gson;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UserLoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception){
        System.err.println(exception.toString());
        Message message = null;
        // 如果是验证码异常
        if (exception instanceof CaptchaException) {
            if (ErrorCodeEnum.U1001007.msg().equals(exception.getMessage())){
                message = new Message(ErrorCodeEnum.U1001007);
            }
            if (ErrorCodeEnum.U1003001.msg().equals(exception.getMessage())){
                message = new Message(ErrorCodeEnum.U1003001);
            }
            if (ErrorCodeEnum.U1009002.msg().equals(exception.getMessage())){
                message = new Message(ErrorCodeEnum.U1009002);
            }
        }
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            message = new Message(ErrorCodeEnum.U1009001);
        }
        String json = new Gson().toJson(message);
        response.setContentType("application/json;charset=utf-8");
        try(PrintWriter out = response.getWriter()) {
            out.write(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
