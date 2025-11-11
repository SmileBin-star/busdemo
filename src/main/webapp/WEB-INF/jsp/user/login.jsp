<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>登录</title>
    <style>
        .login-box { width: 300px; margin: 100px auto; padding: 20px; border: 1px solid #ccc; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 80px; text-align: right; margin-right: 10px; }
        input { width: 200px; padding: 6px; }
        .btn { padding: 8px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .error { color: #dc3545; text-align: center; margin: 10px 0; }
    </style>
</head>
<body>
<div class="login-box">
    <h2 style="text-align: center;">系统登录</h2>
    <form action="/user/login" method="post">
        <div class="form-group">
            <label>用户名：</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>密码：</label>
            <input type="password" name="password" required>
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <div style="text-align: center;">
            <button type="submit" class="btn">登录</button>
        </div>
    </form>
</div>
</body>
</html>