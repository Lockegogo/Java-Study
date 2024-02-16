package com.locke.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service // 注入到容器中
public class UserService {

    // 想拿到 provider-server 提供的票，要去注册中心拿到服务
    // 引用，Pom 坐标，可以定义路径相同的接口名
    @DubboReference
    TicketService ticketService;

    public void bugTicket() {
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心买到" + ticket);
    }

}