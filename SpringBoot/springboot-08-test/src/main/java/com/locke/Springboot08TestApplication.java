package com.locke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
// 开启定时功能的注解
@EnableScheduling
@SpringBootApplication
public class Springboot08TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08TestApplication.class, args);
    }

}
