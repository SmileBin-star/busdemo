package com.example.demo.dao;

import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * 新增线路DTO - 接收前端新增线路（含关联站点）的参数
 */
@Data
public class BusRouteAddDTO {
    @NotBlank(message = "线路名称不能为空")
    @Size(max = 50, message = "线路名称长度不能超过50字符")
    private String routeName; // 线路名称

    @NotNull(message = "起点站ID不能为空")
    @Positive(message = "起点站ID必须为正整数")
    private Integer startStation; // 起点站ID

    @NotNull(message = "终点站ID不能为空")
    @Positive(message = "终点站ID必须为正整数")
    private Integer endStation; // 终点站ID

    @NotNull(message = "总站点数不能为空")
    @Positive(message = "总站点数必须为正整数")
    private Integer totalStations; // 总站点数

    @NotNull(message = "总里程不能为空")
    @DecimalMin(value = "0.00", inclusive = true, message = "总里程不能为负数")
    private BigDecimal totalDistance; // 总里程（公里）

    @NotNull(message = "首班车时间不能为空")
    private LocalTime departureTime; // 首班车时间

    @NotNull(message = "末班车时间不能为空")
    private LocalTime arrivalTime; // 末班车时间

    @NotNull(message = "线路站点列表不能为空")
    @Size(min = 2, message = "线路至少包含2个站点")
    private List<BusRouteStationDTO> stationList; // 关联的站点列表
}

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

/**
 * 修改线路DTO - 接收前端修改线路的参数（不含站点，站点单独维护）
 */
@Data
public class BusRouteUpdateDTO {
    @NotNull(message = "线路ID不能为空")
    @Positive(message = "线路ID必须为正整数")
    private Integer routeId; // 线路ID

    @NotBlank(message = "线路名称不能为空")
    @Size(max = 50, message = "线路名称长度不能超过50字符")
    private String routeName; // 线路名称

    @NotNull(message = "起点站ID不能为空")
    @Positive(message = "起点站ID必须为正整数")
    private Integer startStation; // 起点站ID

    @NotNull(message = "终点站ID不能为空")
    @Positive(message = "终点站ID必须为正整数")
    private Integer endStation; // 终点站ID

    @NotNull(message = "总站点数不能为空")
    @Positive(message = "总站点数必须为正整数")
    private Integer totalStations; // 总站点数

    @NotNull(message = "总里程不能为空")
    @DecimalMin(value = "0.00", inclusive = true, message = "总里程不能为负数")
    private BigDecimal totalDistance; // 总里程（公里）

    @NotNull(message = "首班车时间不能为空")
    private LocalTime departureTime; // 首班车时间

    @NotNull(message = "末班车时间不能为空")
    private LocalTime arrivalTime; // 末班车时间

    @NotNull(message = "线路状态不能为空")
    @Min(value = 0, message = "线路状态只能是0（停运）或1（运营）")
    @Max(value = 1, message = "线路状态只能是0（停运）或1（运营）")
    private Integer status; // 状态（0-停运/1-运营）
}