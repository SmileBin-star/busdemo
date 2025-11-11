<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>${empty user ? '新增用户' : '编辑用户'}</title>
</head>
<body>
<h2>${empty user ? '新增用户' : '编辑用户'}</h2>
<form action="/user/save" method="post">
    <!-- 编辑时需要传递用户ID -->
    <c:if test="${not empty user}">
        <input type="hidden" name="userId" value="${user.userId}">
    </c:if>

    <div>
        <label>用户名：</label>
        <input type="text" name="username" value="${user.username}" required>
    </div>
    <br>

    <div>
        <label>密码：</label>
        <input type="password" name="password" ${empty user ? 'required' : ''}>
        <c:if test="${not empty user}">
            <span>（不填则保持原密码）</span>
        </c:if>
    </div>
    <br>

    <div>
        <label>角色：</label>
        <select name="role">
            <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>管理员</option>
            <option value="super_admin" ${user.role == 'super_admin' ? 'selected' : ''}>超级管理员</option>
        </select>
    </div>
    <br>

    <div>
        <label>状态：</label>
        <select name="status">
            <option value="1" ${empty user || user.status == 1 ? 'selected' : ''}>正常</option>
            <option value="0" ${not empty user && user.status == 0 ? 'selected' : ''}>禁用</option>
        </select>
    </div>
    <br>

    <button type="submit">保存</button>
    <a href="/user/list">取消</a>
</form>
</body>
</html>