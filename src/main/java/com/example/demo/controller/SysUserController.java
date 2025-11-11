package com.example.demo.controller;

import java.util.List;
import org.springframework.ui.Model;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    // 跳转登录页
    @GetMapping("/login")
    public String toLogin() {
        return "user/login"; // 对应login.jsp视图
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        SysUser user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/index";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "user/login";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 清除会话
        return "redirect:/user/login";
    }

    // 跳转用户列表页（仅超级管理员可访问）
    @GetMapping("/list")
    public String list(Model model) {
        List<SysUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    // 新增/修改用户
    @PostMapping("/save")
    public String save(SysUser user, RedirectAttributes redirect) {
        boolean success = userService.saveUser(user);
        redirect.addFlashAttribute("msg", success ? "操作成功" : "操作失败");
        return "redirect:/user/list";
    }

    // 删除用户
    @GetMapping("/delete")
    public String delete(
            @RequestParam(required = true) Integer userId,  // 强制要求参数必须存在
            RedirectAttributes redirect) {

        // 参数校验
        if (userId == null || userId <= 0) {
            redirect.addFlashAttribute("msg", "参数错误：用户ID无效");
            return "redirect:/user/list";
        }

        try {
            boolean success = userService.deleteUser(userId);
            redirect.addFlashAttribute("msg", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            // 捕获异常并返回友好提示
            redirect.addFlashAttribute("msg", "删除失败：" + e.getMessage());
        }
        return "redirect:/user/list";
    }

    // 跳转新增/编辑用户表单页
    @GetMapping("/form")
    public String toForm(@RequestParam(required = false) Integer userId, Model model) {
        // 如果有userId参数，则是编辑操作，查询用户信息回显
        if (userId != null) {
            SysUser user = userService.findById(userId);
            model.addAttribute("user", user);
        }
        return "user/form"; // 对应表单视图
    }

    // 根据ID查询用户（在Service接口和实现类中也需要添加此方法）
    @GetMapping("/findById")
    @ResponseBody
    public SysUser findById(Integer userId) {
        return userService.findById(userId);
    }
}