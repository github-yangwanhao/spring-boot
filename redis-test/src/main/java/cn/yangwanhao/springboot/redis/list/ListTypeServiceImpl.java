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
    public void clearList(String key) {
        stringRedisTemplate.opsForList().trim(key, 0, 0);
        stringRedisTemplate.opsForList().leftPop(key);
    }
}
