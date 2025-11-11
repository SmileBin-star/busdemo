package com.example.demo.entity;

import lombok.Data;
import java.util.Date;

@Data // Lombok注解，自动生成getter/setter/toString
public class SysUser {
    private Integer userId;         // 用户ID（自增主键）
    private String username;        // 用户名（登录账号）
    private String password;        // 密码（加密存储）
    private String role;            // 角色（super_admin/admin）
    private Date createTime;        // 创建时间
    private Integer status;         // 状态（1：正常，0：禁用）
}