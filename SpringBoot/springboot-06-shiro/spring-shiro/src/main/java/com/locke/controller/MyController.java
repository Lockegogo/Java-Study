package com.locke.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello, Shiro");
        return "index";
    }

    @RequestMapping({"user/add"})
    public String add() {
        return "user/add";
    }

    @RequestMapping({"user/update"})
    public String update() {
        return "user/update";
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    // 登录的方法
    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 没有认证过
        // 封装用户的登录数据,获得令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 登录及异常处理
        try {
            // 用户登录
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            // 如果用户名不存在
            System.out.println("用户名不存在");
            model.addAttribute("exception", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            // 如果密码错误
            System.out.println("密码错误");
            model.addAttribute("exception", "密码错误");
            return "login";
        }
    }

    // 退出登录
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}
