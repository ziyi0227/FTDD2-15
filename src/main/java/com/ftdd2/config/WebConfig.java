package com.ftdd2.config;



import com.ftdd2.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
   public void addInterceptors(InterceptorRegistry registry) {
        registry
        .addInterceptor(loginInterceptor)
        .excludePathPatterns("/users/login","/users/register");
    }
}
