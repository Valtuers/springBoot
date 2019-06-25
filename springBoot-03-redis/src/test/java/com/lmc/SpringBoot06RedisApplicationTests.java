package com.lmc;

import com.lmc.bean.User;
import com.lmc.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06RedisApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void contextLoads() {
        /**
         * 储存对象，把对象转化为json，也可以直接储存字符串
         */
        User user = new User();
        user.setName("林铭聪");
        user.setAge(23);
        stringRedisTemplate.opsForValue().set("mapper", JsonUtils.objectToJson(user));
        System.out.println(stringRedisTemplate.opsForValue().get("mapper"));
    }

}
