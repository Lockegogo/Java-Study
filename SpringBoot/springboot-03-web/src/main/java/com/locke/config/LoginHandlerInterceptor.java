package com.locke.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 登录成功之后，应该有用户的 session
        Object loginUser = request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            request.setAttribute("msg","没有权限，请先登录");
            // 转发，服务器跳转
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        } else {
            return true;
        }
    }
}
