<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="zh-CN">
<head>
    <title>公交时刻表列表</title>
</head>
<body>
<h2>公交时刻表管理</h2>
<a href="/schedule/form">新增时刻表</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>线路名称</th>
        <th>车辆</th>
        <th>发车时间</th>
        <th>到达时间</th>
        <th>站点数量</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${schedules}" var="schedule">
        <tr>
            <td>${schedule.scheduleId}</td>
            <td>${schedule.routeName}</td>
            <td>${schedule.vehicle.licensePlate}</td> <!-- 关联显示车牌号 -->
            <td>${schedule.departureTime}</td>
            <td>${schedule.arrivalTime}</td>
            <td>${schedule.stationCount}</td>
            <td>${schedule.status == 1 ? '有效' : '无效'}</td>
            <td>
                <a href="/schedule/form?scheduleId=${schedule.scheduleId}">编辑</a>
                <a href="/schedule/delete?scheduleId=${schedule.scheduleId}"
                   onclick="return confirm('确定要删除该时刻表吗？')">
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