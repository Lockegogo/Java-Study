package com.locke.swagger.controller;

import com.locke.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // /error默认错误请求
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 只要我们的接口中，返回值中存在实体类，他就会被扫描到 Swagger 中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    // Operation 接口：不是放在类上，而是放在方法上
    @ApiOperation("Hello 控制类")
    @GetMapping("/hello2")
    // @ApiParam 给参数加上注释
    public String hello2(@ApiParam("用户名") String username) {
        return "hello" + username;
    }

    @ApiOperation("get 测试")
    @GetMapping("/get")
    public User hello2(@ApiParam("用户") User user) {
        return user;
    }
}
