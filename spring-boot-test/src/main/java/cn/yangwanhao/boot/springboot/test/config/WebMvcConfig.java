package cn.yangwanhao.boot.springboot.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import cn.yangwanhao.boot.springboot.test.interceptors.TokenInterceptor;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/9 19:41
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry
            .addInterceptor(tokenInterceptor)
            .addPathPatterns("/springboot/test/token/**")
            .excludePathPatterns("/springboot/test/token/getToken")
        ;
    }
}
