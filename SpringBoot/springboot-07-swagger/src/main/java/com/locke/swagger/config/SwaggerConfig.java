package com.locke.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
// 开启 Swagger
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    // 获得当前的生产环境
    public Docket docket(Environment environment) {
        // 设置要显示 swagger 的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("LK") // 配置分组
                .apiInfo(apiInfo())
                .enable(b)
                .select() // 通过.select()方法，去配置扫描接口, RequestHandlerSelectors 配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.locke.swagger.controller"))
                .build();
    }

    // 配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Lockegogo", "https://www.cnblogs.com/lockegogo/", "联系人邮箱");
        return new ApiInfo(
                "LK's Swagger", // 标题
                "Life is like a Markov chain.", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>() // 扩展
        );
    }
}
