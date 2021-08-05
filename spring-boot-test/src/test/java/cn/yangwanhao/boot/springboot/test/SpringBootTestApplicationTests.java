package cn.yangwanhao.boot.springboot.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootTestApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testEncryptor() {
        /*
         * 深渊巨坑 springboot 2.x junit test 不支持yml配置文件 必须用properties文件
         */
        String originStr = "******";
        String encryptStr = encryptor.encrypt(originStr);
        String decryptStr = encryptor.decrypt(encryptStr);
        log.info("加密后的字符串:{}", encryptStr);
        log.info("解密后的字符串:{}", decryptStr);
    }

}
