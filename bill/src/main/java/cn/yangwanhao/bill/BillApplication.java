package cn.yangwanhao.bill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/1/11 15:20
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "cn.yangwanhao.bill.dao")
public class BillApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class, args);
    }
}
