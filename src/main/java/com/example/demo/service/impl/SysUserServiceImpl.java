package com.example.demo.service.impl;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    // 登录逻辑：密码加密校验
    @Override
    public SysUser login(String username, String password) {
        // 1. 加密用户输入的密码
        String encryptedPwd = DigestUtils.md5DigestAsHex(password.getBytes());
        // 2. 查询用户（注意：需引入org.springframework.util.DigestUtils）
        SysUser user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(encryptedPwd)) {
            return user;
        }
        return null;
    }

    // 查询所有用户
    @Override
    public List<SysUser> findAll() {
        return userMapper.selectAll();
    }

    // 新增或修改用户
    @Override
    public boolean saveUser(SysUser user) {
        if (user.getUserId() == null) {
            // 新增用户：设置默认值
            user.setCreateTime(new Date());
            user.setStatus(1); // 默认正常状态
            // 密码加密存储
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            return userMapper.insert(user) > 0;
        } else {
            // 修改用户：如果密码不为空则重新加密（否则不修改密码）
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            } else {
                // 避免覆盖原有密码（从数据库查询原有密码）
                SysUser oldUser = userMapper.selectById(user.getUserId());
                user.setPassword(oldUser.getPassword());
            }
            return userMapper.update(user) > 0;
        }
    }

    // 删除用户实现
    @Override
    public boolean deleteUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        // 执行逻辑删除（更新状态为0）
        return userMapper.deleteById(userId) > 0;
    }
    //查询用户
    @Override
    public SysUser findById(Integer userId) {
        return userMapper.selectById(userId);
    }
}