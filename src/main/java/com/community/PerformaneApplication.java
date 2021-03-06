package com.performane;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.performane.mapper")
public class PerformaneApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformaneApplication.class, args);
    }

}
