package cn.yangwanhao.springboot.redis.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.springboot.redis.RedisTestApplication;
import cn.yangwanhao.springboot.redis.list.IListTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 15:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisTestApplication.class})
@Slf4j
public class ListTypeServiceTest {

    @Autowired
    private IListTypeService listTypeService;

    @Test
    public void testPush() {
        String key = "redis:test:list";

        // 先清空list
        listTypeService.clearList(key);

        // Lpush 参数顺序是 cba 实际list顺序是 abc
        Long result = listTypeService.headPush(key, "c", "b", "a");
        log.info("headPush成功后当前list的长度:[{}]", result);
        List<String> list = listTypeService.getList(key);
        log.info("insert之后的list{}", list);

        // Rpush 参数顺序是def 实际list顺序也是 def
        Long result2 = listTypeService.tailPush(key, "d", "e", "f");
        log.info("tailPush成功后当前list的长度:[{}]", result2);
        List<String> list2 = listTypeService.getList(key);
        log.info("insert之后的list{}", list2);
    }
}
