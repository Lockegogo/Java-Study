package com.locke.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

// 服务注册与发现
@DubboService  // 可以被扫描到，在项目一启动就自动注册到注册中心 zookeeper
@Component  // 使用 Dubbo 后尽量不要用 Service 注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《狂神说Java》";
    }
}