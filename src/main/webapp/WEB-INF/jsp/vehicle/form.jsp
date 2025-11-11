<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="zh-CN">
<head>
    <title>${empty vehicle ? '新增车辆' : '编辑车辆'}</title>
    <style>
        .form-item { margin: 10px 0; }
        label { display: inline-block; width: 100px; }
    </style>
</head>
<body>
<h2>${empty vehicle ? '新增公交车辆' : '编辑公交车辆'}</h2>
<form action="/vehicle/save" method="post">
    <c:if test="${not empty vehicle}">
        <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}">
    </c:if>

    <div class="form-item">
        <label>车牌号：</label>
        <input type="text" name="licensePlate" value="${vehicle.licensePlate}" required>
    </div>

    <div class="form-item">
        <label>车型：</label>
        <input type="text" name="model" value="${vehicle.model}" placeholder="例如：比亚迪K9">
    </div>

    <div class="form-item">
        <label>载客量：</label>
        <input type="number" name="capacity" value="${vehicle.capacity}" min="1">
    </div>

    <div class="form-item">
        <label>状态：</label>
        <select name="status">
            <option value="1" ${empty vehicle || vehicle.status == 1 ? 'selected' : ''}>正常运营</option>
            <option value="0" ${not empty vehicle && vehicle.status == 0 ? 'selected' : ''}>维修中</option>
        </select>
    </div>

    <div class="form-item">
        <label>购置日期：</label>
        <input type="date" name="purchaseDate" value="${vehicle.purchaseDate}">
    </div>

    <div class="form-item">
        <button type="submit">保存</button>
        <a href="/vehicle/list">取消</a>
    </div>
</form>
</body>
</html>