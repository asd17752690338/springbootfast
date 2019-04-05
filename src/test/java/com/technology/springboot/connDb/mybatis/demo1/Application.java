package com.technology.springboot.connDb.mybatis.demo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.technology.springboot.connDb.mybatis.demo1")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}