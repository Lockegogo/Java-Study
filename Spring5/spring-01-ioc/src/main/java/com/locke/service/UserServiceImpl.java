package com.locke.service;

import com.locke.dao.UserDao;
import com.locke.dao.UserDaoImpl;
import com.locke.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService{
    // 业务层就做一件事情：将 Dao 引入 Service
    // private UserDao userDao = new UserDaoMysqlImpl();
    private UserDao userDao;
    // 利用 set 进行动态实现值的注入！
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void getUser() {
        userDao.getUser();

    }
}
