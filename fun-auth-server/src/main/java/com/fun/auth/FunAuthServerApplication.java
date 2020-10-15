package com.fun.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MrDJun 2020/10/15
 */
@MapperScan("com.fun.auth.mapper")
@SpringBootApplication
public class FunAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunAuthServerApplication.class, args);
    }
}
