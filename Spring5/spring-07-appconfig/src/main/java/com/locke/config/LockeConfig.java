package com.locke.config;

import com.locke.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.locke.pojo")
public class LockeConfig {
    @Bean
    public User getUser() {
        return new User();
    }
}
