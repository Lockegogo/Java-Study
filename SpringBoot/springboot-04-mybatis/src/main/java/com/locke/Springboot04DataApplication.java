package com.locke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.locke.mapper")
public class Springboot04DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot04DataApplication.class, args);
    }

}
