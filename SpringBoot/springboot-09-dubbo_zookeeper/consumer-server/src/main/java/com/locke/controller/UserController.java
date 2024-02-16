package com.locke.controller;

import com.locke.service.TicketService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

     @DubboReference  // 引用服务
     TicketService ticketService;

     @GetMapping("/buy")
     public String buyTicket() {
         return ticketService.getTicket();
     }

}
