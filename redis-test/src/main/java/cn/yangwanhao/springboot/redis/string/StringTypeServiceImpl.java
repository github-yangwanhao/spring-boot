package cn.yangwanhao.springboot.redis.string;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2022/4/21 14:02
 */
@Service
public class StringTypeServiceImpl implements IStringTypeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setStr(String key, String value, Integer time) {
        if (time == null || time == 0) {
            stringRedisTemplate.opsForValue().set(key, value);
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }
}
