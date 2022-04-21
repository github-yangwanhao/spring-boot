package cn.yangwanhao.springboot.redis.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 15:04
 */
@Service
public class ListTypeServiceImpl implements IListTypeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Long headPush(String key, String... value) {
        return stringRedisTemplate.opsForList()
            .leftPushAll(key, value);
    }

    @Override
    public Long tailPush(String key, String... value) {
        return stringRedisTemplate.opsForList()
            .rightPushAll(key, value);
    }

    @Override
    public List<String> getList(String key) {
        return stringRedisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public String getValueFromIndex(String key, Integer index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    @Override
    public String headPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    @Override
    public String tailPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    @Override
    public void trim(String key, Integer start, Integer end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public Long getListSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    @Override
    public void clearList(String key) {
        stringRedisTemplate.opsForList().trim(key, 0, 0);
        stringRedisTemplate.opsForList().leftPop(key);
    }
}
