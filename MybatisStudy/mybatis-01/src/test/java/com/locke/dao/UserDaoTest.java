package com.locke.dao;

import com.locke.pojo.User;
import com.locke.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void selectUser() {
        // 第一步：获取 SqlSession 对象
        SqlSession session = MybatisUtils.getSession();
        // 第二步：执行 SQL
        //方法一:
        //List<User> users = session.selectList("com.locke.mapper.UserMapper.selectUser");
        //方法二:
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectUser();
        for (User user: users){
            System.out.println(user);
        }
        // 第三步：关闭 sqlSession
        session.close();
    }
}

