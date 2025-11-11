<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>${empty arrival ? '新增到站记录' : '编辑到站记录'}</title>
    <style>
        .form-item { margin: 10px 0; }
        label { display: inline-block; width: 120px; }
    </style>
</head>
<body>
<h2>${empty arrival ? '新增到站记录' : '编辑到站记录'}</h2>
<form action="/arrival/save" method="post">
    <c:if test="${not empty arrival}">
        <input type="hidden" name="recordId" value="${arrival.recordId}">
    </c:if>

    <div class="form-item">
        <label>所属时刻表：</label>
        <select name="scheduleId" required>
            <option value="">-- 选择时刻表 --</option>
            <c:forEach items="${schedules}" var="s">
                <option value="${s.scheduleId}"
                    ${not empty arrival && arrival.scheduleId == s.scheduleId ? 'selected' : ''}>
                        ${s.routeName}（${s.departureTime}发车）
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-item">
        <label>站点名称：</label>
        <input type="text" name="stationName" value="${arrival.stationName}" required>
    </div>

    <div class="form-item">
        <label>实际到站时间：</label>
        <input type="datetime-local" name="actualArrivalTime" value="${arrival.actualArrivalTime}" required>
    </div>

    <div class="form-item">
        <label>延迟分钟数：</label>
        <input type="number" name="delayMinutes" value="${arrival.delayMinutes}" min="0"
               placeholder="0表示准点">
    </div>

    <div class="form-item">
        <button type="submit">保存</button>
        <a href="/arrival/list">取消</a>
    </div>
</form>
</body>
</html>