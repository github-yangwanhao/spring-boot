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

    String key = "redis:test:list";

    @Test
    public void testPush() {
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

    @Test
    public void testGetValueFromIndex() {
        String index0 = listTypeService.getValueFromIndex(key, 0);
        String index2 = listTypeService.getValueFromIndex(key, 2);
        String index4 = listTypeService.getValueFromIndex(key, 4);
        log.info("list[{}]的第[{}]个元素的值是[{}]", key, 0, index0);
        log.info("list[{}]的第[{}]个元素的值是[{}]", key, 2, index2);
        log.info("list[{}]的第[{}]个元素的值是[{}]", key, 4, index4);
    }

    @Test
    public void testGetListSize() {
        Long listSize = listTypeService.getListSize(key);
        log.info("list[{}]的长度是[{}]", key, listSize);
    }

    @Test
    public void testPop() {
        // 初始化list
        testPush();

        String headPop = listTypeService.headPop(key);
        List<String> list = listTypeService.getList(key);
        log.info("headPop出来的元素是:[{}],headPop之后的list{}", headPop, list);

        String tailPop = listTypeService.tailPop(key);
        List<String> list2 = listTypeService.getList(key);
        log.info("tailPop出来的元素是:[{}],tailPop之后的list{}", tailPop, list2);

    }

    @Test
    public void testTrim() {
        // 初始化
        testPush();
        int start = 1;
        int end = -2;
        listTypeService.trim(key, start,end);
        List<String> list = listTypeService.getList(key);
        log.info("trim的范围是[{}]~[{}],trim之后的list{}", start, end, list);
    }
}
