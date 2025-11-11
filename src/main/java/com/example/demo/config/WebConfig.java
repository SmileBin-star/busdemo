package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // 标识为Spring配置类
public class WebConfig implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 实例化登录拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        // 2. 注册拦截器，并配置拦截规则
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有路径（/** 表示所有路径及其子路径）
                .excludePathPatterns(   // 排除不需要拦截的路径（登录页、登录请求、静态资源等）
                        "/user/login",       // 登录页请求
                        "/user/loginSubmit", // 登录提交接口（如果单独分离的话）
                        "/static/**",        // 静态资源（CSS、JS、图片等）
                        "/error"             // 错误页
                );
    }
}

