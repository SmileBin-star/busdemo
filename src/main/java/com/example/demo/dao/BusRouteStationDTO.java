package com.example.demo.dao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * 线路-站点关联子DTO - 新增线路时的站点关联参数
 */
@Data
public class BusRouteStationDTO {
    @NotNull(message = "站点ID不能为空")
    @Positive(message = "站点ID必须为正整数")
    private Integer stationId; // 站点ID

    @NotNull(message = "站点顺序不能为空")
    @Positive(message = "站点顺序必须为正整数")
    private Integer stationOrder; // 站点顺序

    @NotNull(message = "距前一站距离不能为空")
    @DecimalMin(value = "0.00", inclusive = true, message = "距前一站距离不能为负数")
    private BigDecimal distanceFromPrev; // 距前一站距离（公里）

    @NotNull(message = "预计通过时间不能为空")
    @Min(value = 0, message = "预计通过时间不能为负数")
    private Integer estimatedTime; // 预计通过时间（分钟）
}

