package com.ftdd2.interceptor;




import com.ftdd2.utils.JwtUtil;
import com.ftdd2.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
      String token = request.getHeader("token");
      try{
          //redis 获取相同token
          ValueOperations<String,String>operations=stringRedisTemplate.opsForValue();
          String redisToken = operations.get(token);
          if(redisToken== null){
              throw new RuntimeException();
          }
          Map<String,Object>claims= JwtUtil.parseToken(token);
          ThreadLocalUtil.set(claims);

          return true;
      }catch(Exception e){
          response.setStatus(401);
          return false;
      }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }

}
