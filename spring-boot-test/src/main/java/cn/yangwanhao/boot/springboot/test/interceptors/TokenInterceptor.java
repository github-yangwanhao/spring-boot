package cn.yangwanhao.boot.springboot.test.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.yangwanhao.support.LoginUserInfo;
import cn.yangwanhao.util.AppParamUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/9 19:42
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${token.signature}")
    private String signature;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return false;
        }
        Algorithm algorithm = Algorithm.HMAC256(signature);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
            .build();
        DecodedJWT jwt = jwtVerifier.verify(token);

        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserId(jwt.getClaim("userId").asString());
        loginUserInfo.setUserName(jwt.getClaim("userName").asString());
        loginUserInfo.setUserMobile(jwt.getClaim("userMobile").asString());
        loginUserInfo.setUserEmail(jwt.getClaim("userEmail").asString());
        String rolesJson = jwt.getClaim("roles")
            .asString();
        loginUserInfo.setRoles(JSONArray.parseArray(rolesJson, String.class));

        AppParamUtil.setLoginUser(loginUserInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AppParamUtil.removeAll();
    }

}