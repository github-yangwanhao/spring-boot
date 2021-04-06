package cn.yangwanhao.boot.transaction.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangwanhao
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.yangwanhao.boot.transaction.test.dao"})
public class TransactionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionTestApplication.class, args);
    }

}
