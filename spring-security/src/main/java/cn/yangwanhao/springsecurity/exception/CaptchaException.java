package cn.yangwanhao.springsecurity.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author 青鲤
 */
public class CaptchaException extends AuthenticationException {

    private static final long serialVersionUID = -3893758439450846712L;

    public CaptchaException(String message) {
        super(message);
    }
}
