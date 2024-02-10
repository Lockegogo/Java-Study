package com.locke.demos.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BasicController {

    @RequestMapping({"/", "/index.html", "/index"})
    public String index() {
        return "index.html";
    }



}
