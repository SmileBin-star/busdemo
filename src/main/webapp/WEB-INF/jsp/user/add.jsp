<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>新增用户</title>
    <style>
        .form-box { width: 400px; margin: 50px auto; }
        .form-item { margin: 10px 0; }
    </style>
</head>
<body>
<div class="form-box">
    <h2>新增用户</h2>
    <form action="/user/save" method="post">
        <!-- 编辑时需要传递userId，新增时不显示 -->
        <input type="hidden" name="userId" value="${user.userId}">

        <div class="form-item">
            <label>用户名：</label>
            <input type="text" name="username" value="${user.username}" required>
        </div>

        <div class="form-item">
            <label>密码：</label>
            <input type="password" name="password" placeholder="新增必填，编辑留空则不修改">
        </div>

        <div class="form-item">
            <label>角色：</label>
            <select name="role">
                <option value="admin" ${user.role eq 'admin' ? 'selected' : ''}>普通管理员</option>
                <option value="super_admin" ${user.role eq 'super_admin' ? 'selected' : ''}>超级管理员</option>
            </select>
        </div>

        <div class="form-item">
            <label>状态：</label>
            <select name="status">
                <option value="1" ${user.status eq 1 ? 'selected' : ''}>正常</option>
                <option value="0" ${user.status eq 0 ? 'selected' : ''}>禁用</option>
            </select>
        </div>

        <div class="form-item">
            <button type="submit">保存</button>
            <button type="button" onclick="history.back()">取消</button>
        </div>
    </form>
</div>
</body>
</html>