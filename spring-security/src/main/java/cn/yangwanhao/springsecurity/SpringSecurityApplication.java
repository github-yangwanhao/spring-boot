package cn.yangwanhao.springsecurity;

import cn.yangwanhao.springsecurity.common.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }

}
