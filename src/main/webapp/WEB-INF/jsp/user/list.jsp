<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="zh-CN">
<head>
    <title>用户列表</title>
</head>
<body>
<h2>用户管理</h2>
<a href="/user/form">新增用户</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>角色</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <td>${user.role}</td>
            <td>${user.status == 1 ? '正常' : '禁用'}</td>
            <td>
                <a href="/user/form?userId=${user.userId}">编辑</a>
                <a href="/user/delete?userId=${user.userId}"
                   onclick="return confirm('确定要删除ID为${user.userId}的用户吗？')">
                    删除
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty msg}">
    <script>alert('${fn:escapeXml(msg)}')</script>
</c:if>
</body>
</html>