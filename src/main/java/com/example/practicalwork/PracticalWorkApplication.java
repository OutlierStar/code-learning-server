package com.example.practicalwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.practicalwork.Mapper.**")
public class PracticalWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticalWorkApplication.class, args);
    }

}