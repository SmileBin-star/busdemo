<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="zh-CN">
<head>
    <title>公交到站记录列表</title>
</head>
<body>
<h2>公交到站记录管理</h2>
<a href="/arrival/form">新增到站记录</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>所属线路</th>
        <th>站点名称</th>
        <th>实际到站时间</th>
        <th>延迟分钟数</th>
        <th>记录时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${arrivals}" var="arrival">
        <tr>
            <td>${arrival.recordId}</td>
            <td>${arrival.schedule.routeName}</td> <!-- 关联显示线路名称 -->
            <td>${arrival.stationName}</td>
            <td>${arrival.actualArrivalTime}</td>
            <td>${arrival.delayMinutes == 0 ? '准点' : arrival.delayMinutes + '分钟'}</td>
            <td>${arrival.createTime}</td>
            <td>
                <a href="/arrival/form?recordId=${arrival.recordId}">编辑</a>
                <a href="/arrival/delete?recordId=${arrival.recordId}"
                   onclick="return confirm('确定要删除该记录吗？')">
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