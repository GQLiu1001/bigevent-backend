package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
public class RedisTest {

    private static final Logger logger = Logger.getLogger(RedisTest.class.getName());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testConnection() {
        try {
            redisTemplate.getConnectionFactory().getConnection().ping();

            logger.log(Level.INFO, "Connected to Redis successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to connect to Redis: " + e.getMessage(), e);
        }
    }

    @Test
    public void testSetAndGet() {
        try {
            redisTemplate.opsForValue().set("name", "zhangsan");
            String name = redisTemplate.opsForValue().get("name");
            logger.log(Level.INFO, "Retrieved value: " + name);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to set or get value from Redis: " + e.getMessage(), e);
        }
    }

    @Test
    //获取
    public void testGet() {
        try {
            String name = redisTemplate.opsForValue().get("name");
            System.out.println(name);
            logger.log(Level.INFO, "Retrieved value: " + name);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to get value from Redis: " + e.getMessage(), e);
        }
    }
}