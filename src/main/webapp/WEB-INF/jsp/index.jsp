<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>系统首页</title>
</head>
<body>
<div style="text-align: right; margin: 20px;">
    欢迎您，${loginUser.username}！
    <a href="/user/logout">退出登录</a>
</div>
<h1 style="text-align: center;">公交信息管理系统</h1>
<div style="text-align: center; margin: 50px;">
    <a href="/line/list" style="margin: 0 20px;">公交线路管理</a>
    <a href="/station/list" style="margin: 0 20px;">公交站点管理</a>
    <a href="/vehicle/list" style="margin: 0 20px;">公交车辆管理</a>
    <a href="/schedule/list" style="margin: 0 20px;">公交时刻表管理</a>
    <a href="/arrival/list" style="margin: 0 20px;">公交到站记录管理</a>
    <c:if test="${loginUser.role eq 'super_admin'}">
        <a href="/user/list" style="margin: 0 20px;">用户管理</a>
    </c:if>
</div>
</body>
</html>