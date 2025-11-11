package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 处理 /index 请求，返回首页视图
    @GetMapping("/index")
    public String index() {
        // 假设首页视图为 /WEB-INF/jsp/index.jsp
        return "index";
    }
}