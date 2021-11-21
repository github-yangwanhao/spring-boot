package cn.yangwanhao.springsecurity.config.security;

import cn.yangwanhao.springsecurity.common.GlobalConstant;
import cn.yangwanhao.springsecurity.filter.CaptchaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserLoginAuthenticationSuccessHandler successHandler;
    @Autowired
    private UserLoginAuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new CaptchaFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .successForwardUrl(GlobalConstant.Uri.LOGIN_SUCCESS)
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage(GlobalConstant.Uri.LOGIN_PAGE)
                .loginProcessingUrl(GlobalConstant.Uri.LOGIN)
                .and()
                .authorizeRequests()
                .antMatchers(GlobalConstant.Uri.FREE_URLS).permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }
}
