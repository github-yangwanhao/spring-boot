package cn.yangwanhao.springboot.redis.test.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.springboot.redis.RedisTestApplication;
import cn.yangwanhao.springboot.redis.string.IStringTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisTestApplication.class})
@Slf4j
public class StringTypeServiceTest {

    @Autowired
    private IStringTypeService stringTypeService;

    @Test
    public void test() {
        String key = "a";
        stringTypeService.setStr(key, "a", 0);
        String value = stringTypeService.getStr(key);
        System.out.println(value);
    }
}
