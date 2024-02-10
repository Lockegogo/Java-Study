package com.locke.dao;

public class UserDaoMysqlImpl implements UserDao {

    // 在后台添加一个新的方法
    @Override
    public void getUser() {
        System.out.println("Mysql获取用户数据");
    }
}
