<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="zh-CN">
<head>
    <title>公交车辆列表</title>
</head>
<body>
<h2>公交车辆管理</h2>
<a href="/vehicle/form">新增车辆</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>车牌号</th>
        <th>车型</th>
        <th>载客量</th>
        <th>状态</th>
        <th>购置日期</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.vehicleId}</td>
            <td>${vehicle.licensePlate}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.capacity}</td>
            <td>${vehicle.status == 1 ? '正常' : '维修'}</td>
            <td>${vehicle.purchaseDate}</td>
            <td>
                <a href="/vehicle/form?vehicleId=${vehicle.vehicleId}">编辑</a>
                <a href="/vehicle/delete?vehicleId=${vehicle.vehicleId}"
                   onclick="return confirm('确定要删除车牌号为${vehicle.licensePlate}的车辆吗？')">
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