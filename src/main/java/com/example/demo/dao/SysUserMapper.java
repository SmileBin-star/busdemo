package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysUserMapper {

    // 根据用户名查询
    SysUser selectByUsername(String username);

    // 查询所有用户
    List<SysUser> selectAll();

    // 根据ID查询
    SysUser selectById(Integer userId);

    // 新增用户
    int insert(SysUser user);

    // 修改用户
    int update(SysUser user);

    //根据ID删除用户（逻辑删除）
    int deleteById(Integer userId);

}