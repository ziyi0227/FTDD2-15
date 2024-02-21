package com.ftdd2.utils;

import com.alibaba.fastjson2.JSON;
import com.ftdd2.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.alibaba.fastjson2.JSON.parseObject;

@Component
public class JwtUtil {

    // 有效期
    private static final long JWT_EXPIRE = 3*60*60*1000L;  //3小时
    // 令牌秘钥
    private static final String JWT_KEY = "123456";

    public  String createToken(Object data){
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 过期时间
        long expTime = currentTime+JWT_EXPIRE;
        // 构建jwt
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))
                .setExpiration(new Date(expTime));
        return builder.compact();
    }

    private SecretKey encodeSecret(String key){
        byte[] encode = Base64.getEncoder().encode(key.getBytes());
        SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");
        return  aes;
    }

    public  Claims parseToken(String token){
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

    public <T> T parseToken(String token,Class<T> clazz){
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(),clazz);
    }

}
