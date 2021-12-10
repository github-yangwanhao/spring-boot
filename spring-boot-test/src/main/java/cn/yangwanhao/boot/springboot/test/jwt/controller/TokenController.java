package cn.yangwanhao.boot.springboot.test.jwt.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cn.yangwanhao.support.BaseController;
import cn.yangwanhao.support.LoginUserInfo;
import cn.yangwanhao.util.DateUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/9 20:11
 */
@Slf4j
@RestController
@RequestMapping("/springboot/test/token")
public class TokenController extends BaseController {

    private static final long EXPIRE_MILLION_SECOND = 1000*60*5L;

    @Value("${token.signature}")
    private String signature;

    @PostMapping("/getToken")
    public String test1(@RequestBody LoginUserInfo loginUserInfo) {
        Date expireTime = new Date(System.currentTimeMillis() + EXPIRE_MILLION_SECOND);
        log.info("token失效时间截止:{}", DateUtils.getDateString(expireTime));
        Algorithm algorithm = Algorithm.HMAC256(signature);
        String sign = JWT.create()
            .withClaim("userId", loginUserInfo.getUserId())
            .withClaim("userName", loginUserInfo.getUserName())
            .withClaim("userMobile", loginUserInfo.getUserMobile())
            .withClaim("userEmail", loginUserInfo.getUserEmail())
            .withClaim("roles", JSONObject.toJSONString(loginUserInfo.getRoles()))
            .withExpiresAt(expireTime)
            .sign(algorithm);
        log.info("生成的加密字符串是:{}", sign);
        return sign;
    }

    @GetMapping("/getLoginUserInfo")
    public LoginUserInfo getLoginUserInfoTest() {
        LoginUserInfo loginUserInfo = super.getLoginUserInfo();
        log.info("当前位置:TokenController#getLoginUserInfo;当前登录用户信息:{}", JSONObject.toJSONString(loginUserInfo));
        return loginUserInfo;
    }


}
