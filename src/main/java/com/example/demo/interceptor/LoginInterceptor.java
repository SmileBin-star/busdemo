package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

// 登录拦截器：未登录用户禁止访问系统内页
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取Session中的登录用户
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        // 2. 如果未登录，重定向到登录页，返回false（阻止请求继续）
        if (loginUser == null) {
            // 重定向到登录页
            response.sendRedirect("/user/login");
            return false;
        }

        // 3. 已登录，允许请求继续执行
        return true;
    }
}