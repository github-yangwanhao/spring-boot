package cn.yangwanhao.springsecurity;

import cn.yangwanhao.springsecurity.common.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cn.yangwanhao.springsecurity.common.util.CaptchaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            System.out.println(CaptchaUtil.getCaptcha());
        }
    }

    @Test
    public void run1() {
        stringRedisTemplate.opsForValue().set("bbb","bbb",1, TimeUnit.MINUTES);
        stringRedisTemplate.opsForList().rightPushAll("code","asdfgh","1");
    }

    @Test
    public void run2() throws InterruptedException {
        User user = new User(1, "aaa", 18, "******");
        redisTemplate.opsForValue().set("user1", user, 5, TimeUnit.MINUTES);
        User user1 = redisTemplate.opsForValue().get("user1");
        Thread.sleep(10000);
        Long expire = redisTemplate.getExpire("user1");
        System.err.println(expire);
        System.out.println(user1);
        user1.setAge(user1.getAge()-1);
        redisTemplate.opsForValue().set("user1", user, expire, TimeUnit.SECONDS);
        System.err.println(redisTemplate.getExpire("user1"));
    }

    @Test
    public void run3() throws InterruptedException {
        String key = "str1";
        stringRedisTemplate.opsForValue().set(key,"bbb",5, TimeUnit.MINUTES);
        Thread.sleep(2000);
        Long expire = stringRedisTemplate.getExpire(key);
        System.out.println(expire);
        Thread.sleep(10000);
        Long expire1 = stringRedisTemplate.getExpire(key);
        System.err.println(expire1);
        Thread.sleep(20000);
        stringRedisTemplate.opsForValue().set(key, "aaa", 0);
        System.out.println(stringRedisTemplate.getExpire(key));
    }

    @Test
    public void run4() {
        User user = new User(1, "青鲤", 18, "xujuan");
        Gson g = new GsonBuilder().create();
        stringRedisTemplate.opsForValue().set("user", g.toJson(user));
        String userJson = stringRedisTemplate.opsForValue().get("user");
        System.out.println(userJson);
        User user1 = g.fromJson(userJson, User.class);
        System.err.println(user1);
    }

}
