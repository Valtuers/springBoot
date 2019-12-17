package com.lmc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lmc.dao")
public class Springboot08ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08ShiroApplication.class, args);
    }

}
