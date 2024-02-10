package com.locke.dao;

import com.locke.pojo.User;

import java.util.List;
public interface UserMapper {
    List<User> selectUser();
}
