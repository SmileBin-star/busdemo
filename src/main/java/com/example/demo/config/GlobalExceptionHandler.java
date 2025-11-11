package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理参数错误
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("msg", "错误：" + e.getMessage());
        return "redirect:/user/list";
    }

    // 处理其他异常
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("msg", "系统错误：" + e.getMessage());
        return "redirect:/user/list";
    }
}