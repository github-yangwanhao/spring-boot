package cn.yangwanhao.boot.exception.retry.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = "cn.yangwanhao.boot.exception.retry.demo.mapper")
public class ExceptionRetryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionRetryDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
