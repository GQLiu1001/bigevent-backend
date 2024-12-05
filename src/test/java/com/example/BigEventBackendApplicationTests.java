package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigEventBackendApplicationTests {
    //JWT
    @Test
    void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        //生成jwt代码
        String token = JWT.create()
                .withClaim("user", claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//添加过期时间 当前时间+1h
                .sign(Algorithm.HMAC256("rabbittank"));
        System.out.println(token);


    }
    @Test
    void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MzI5NzQ4MzN9." +
                "u_MEX3p060_DKQznlkXmQnS6J57vAqhMr5bvRBYm_KE";
        System.out.println(JWT.decode(token).getClaim("user").asMap());
    }
}
