package cn.yangwanhao.news_mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/12 16:20
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "cn.yangwanhao.news_mail.dao")
public class NewsMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsMailApplication.class, args);
    }

}
