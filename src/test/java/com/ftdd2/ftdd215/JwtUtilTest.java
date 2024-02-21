package com.ftdd2.ftdd215;

import com.ftdd2.properties.JwtProperties;
import com.ftdd2.sys.entity.Users;
import com.ftdd2.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        Users user = new Users();
        user.setUsername("admin");
        user.setPhone("123456");
        String jwt = jwtUtil.createToken(user);
        System.out.println(jwt);
    }

    @Test
    public void testParesJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0MzkyMzA0OS03NjgxLTQ0MjctOThhZS1kODhmYmU0NTVkZjkiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5XCIsXCJ1c2VybmFtZVwiOlwiemhhbmdzYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE3MDg1MDY0ODksImV4cCI6MTcwODUxNzI4OX0.AbBROAo-7MJbILMkI7nYNdiXwz48FaX2Dps3WPx9njY";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }
}
