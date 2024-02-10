package com.locke.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// 等价于 <bean id="cat" class="com.locke.pojo.Cat">
@Component
public class Cat {
    @Value("花花")
    public String name;
}
