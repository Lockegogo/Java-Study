package com.locke;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.locke.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot10RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() throws JsonProcessingException {

        // redisTemplate: 操作不同的数据类型，api 和我们的指令是一样的
        // redisTemplate.opsForValue(): 表示操作字符串 类似 String
        // redisTemplate.opsForList(): 表示操作 List ,类似 list
        redisTemplate.opsForValue().set("user", "locke");
        System.out.println(redisTemplate.opsForValue().get("user"));

        // 除了基本的操作，我们常用的方法都可以直接通过 redisTemplate 操作，比如事务和基本的 CRUD
    }

    @Test
    public void testRedis() {
        redisUtil.lSet("key", "value");
        // 获取 key 的值
        System.out.println(redisUtil.lGet("key", 0, -1));
    }
}
