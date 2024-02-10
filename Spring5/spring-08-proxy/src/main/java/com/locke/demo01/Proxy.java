package com.locke.demo01;

public class Proxy implements Rent {
    // 尽量用组合而不是继承
    private Host host;

    // 无参构造
    public Proxy() {
    }

    // 有参构造
    public Proxy(Host host) {
        this.host = host;
    }
    // 传给它的是哪个客户，就调用哪个客户去租房
    @Override
    public void rent() {
        seeHouse();
        host.rent();

    }

    // 看房
    public void seeHouse(){
        System.out.println("中介带你看房");

    }
}
