package com.rootZero.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
   @Disabled
     void testSendMail(){
        redisTemplate.opsForValue().set("email","piraj@gmail.com");

        Object email = redisTemplate.opsForValue().get("email");

    }
}
