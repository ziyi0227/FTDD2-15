package com.ftdd2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ftdd2.*.mapper")
public class Ftdd215Application {

    public static void main(String[] args) {
        SpringApplication.run(Ftdd215Application.class, args);
    }

}
