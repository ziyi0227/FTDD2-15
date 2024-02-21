package com.ftdd2;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@SpringBootApplication
@MapperScan("com.ftdd2.*.mapper")
public class Ftdd215Application {

    public static void main(String[] args) {
        SpringApplication.run(Ftdd215Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
