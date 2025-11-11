<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>${empty schedule ? '新增时刻表' : '编辑时刻表'}</title>
    <style>
        .form-item { margin: 10px 0; }
        label { display: inline-block; width: 120px; }
    </style>
</head>
<body>
<h2>${empty schedule ? '新增公交时刻表' : '编辑公交时刻表'}</h2>
<form action="/schedule/save" method="post">
    <c:if test="${not empty schedule}">
        <input type="hidden" name="scheduleId" value="${schedule.scheduleId}">
    </c:if>

    <div class="form-item">
        <label>线路名称：</label>
        <input type="text" name="routeName" value="${schedule.routeName}" required placeholder="例如：101路（火车站-汽车站）">
    </div>

    <div class="form-item">
        <label>关联车辆：</label>
        <select name="vehicleId" required>
            <option value="">-- 选择车辆 --</option>
            <c:forEach items="${vehicles}" var="v">
                <option value="${v.vehicleId}"
                    ${not empty schedule && schedule.vehicleId == v.vehicleId ? 'selected' : ''}>
                        ${v.licensePlate}（${v.model}）
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-item">
        <label>发车时间：</label>
        <input type="time" name="departureTime" value="${schedule.departureTime}" required>
    </div>

    <div class="form-item">
        <label>到达时间：</label>
        <input type="time" name="arrivalTime" value="${schedule.arrivalTime}" required>
    </div>

    <div class="form-item">
        <label>站点数量：</label>
        <input type="number" name="stationCount" value="${schedule.stationCount}" min="2">
    </div>

    <div class="form-item">
        <label>状态：</label>
        <select name="status">
            <option value="1" ${empty schedule || schedule.status == 1 ? 'selected' : ''}>有效</option>
            <option value="0" ${not empty schedule && schedule.status == 0 ? 'selected' : ''}>无效</option>
        </select>
    </div>

    <div class="form-item">
        <button type="submit">保存</button>
        <a href="/schedule/list">取消</a>
    </div>
</form>
</body>
</html>