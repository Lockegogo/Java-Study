package com.locke.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 自定义的 Realm
public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 打印一个提示
        System.out.println("执行了授权方法");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 打印一个提示
        System.out.println("执行了认证方法");

        // 用户名密码 (暂时先自定义一个做测试)
        String name = "root";
        String password = "1234";

        // 1. 用户名认证
        // 通过参数获取登录的控制器中生成的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 用户名认证
        if (!token.getUsername().equals(name)){
            // 用户名不存在，shiro 底层就会抛出 UnknownAccountException 异常
            // return null 就表示控制器中抛出的相关异常
            return null;
        }
        // 2. 密码认证， Shiro 自己做，为了避免和密码的接触
        // 最后返回一个 AuthenticationInfo 接口的实现类，这里选择 SimpleAuthenticationInfo
        // 三个参数：获取当前用户的认证, 密码, 认证名
        return new SimpleAuthenticationInfo("", password, "");
    }
}