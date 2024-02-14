package com.locke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/HelloController")
public class HelloController {
    //真实访问地址 : 项目名/HelloController/hello
    @RequestMapping("/hello")
    public String sayHello(Model model){
        // 向模型中添加属性 msg 与值，可以在 JSP 页面中取出并渲染
        model.addAttribute("msg","hello, SpringMVC");
        // web-inf/jsp/hello.jsp
        return "hello";
    }
}
