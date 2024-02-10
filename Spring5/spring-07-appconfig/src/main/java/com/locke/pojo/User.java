package com.locke.pojo;

import org.springframework.beans.factory.annotation.Value;
// 这个注解的含义：说明类被 Spring 接管了，注册到了容器中
// @Component
public class User {
    private String name;

    public String getName() {
        return name;
    }
    @Value("花花")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
