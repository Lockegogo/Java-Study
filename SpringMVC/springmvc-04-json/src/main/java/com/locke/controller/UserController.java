package com.locke.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locke.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/json1")
    @ResponseBody // 它就不会走视图解析器，会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        // 创建一个 jackson 的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        User user = new User("LK", 26, "女性");
        // 将我们的对象解析成为 json 格式
        String str = mapper.writeValueAsString(user);
        // 由于 @ResponseBody 注解，这里会将 str 转成 json 格式返回；十分方便
        return str;
    }
}
