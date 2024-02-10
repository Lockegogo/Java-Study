package com.locke;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

@SpringBootTest
class Springboot08TestApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    // 邮件设置 1：一个简单的邮件
    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("洛克王，你好");
        mailMessage.setText("Life is like a Markov chain.");

        mailMessage.setTo("1073517093@qq.com");
        mailMessage.setFrom("1073517093@qq.com");
        javaMailSender.send(mailMessage);
    }

    // 一个复杂的邮件
    @Test
    void contextLoads2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // 组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // 正文
        helper.setSubject("洛克王，你好~plus");
        helper.setText("<p style='color:red'>Life is like a Markov chain.</p>", true);

        // 附件
        helper.addAttachment("1.jpg", new File("C:\\Users\\Lockegogo\\Pictures\\jeremy-perkins-UgNjyPkphtU-unsplash.jpg"));
        // helper.addAttachment("2.jpg", new File(""));

        helper.setTo("1073517093@qq.com");
        helper.setFrom("1073517093@qq.com");

        javaMailSender.send(mimeMessage);

    }

}
