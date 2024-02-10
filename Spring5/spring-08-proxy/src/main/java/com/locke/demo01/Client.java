package com.locke.demo01;

public class Client {
    public static void main(String[] args) {
        // 房东要租房子
        Host host = new Host();
        // 中介帮房租租房子
        // 但是代理角色一般会有一些附属操作
        Proxy proxy = new Proxy(host);
        // 你不同面对房顶，直接找中介租房
        proxy.rent();
    }
}
