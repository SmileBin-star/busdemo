package com.example.demo.service;

import com.example.demo.entity.SysUser;
import java.util.List;

public interface SysUserService {

    // 用户登录（返回用户信息或null）
    SysUser login(String username, String password);

    // 查询所有用户
    List<SysUser> findAll();

    // 新增或修改用户
    boolean saveUser(SysUser user);

    // 删除用户
    boolean deleteUser(Integer userId);

    // 根据ID查询用户
    SysUser findById(Integer userId);
}