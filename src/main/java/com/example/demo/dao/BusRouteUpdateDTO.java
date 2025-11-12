package com.example.demo.dao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

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
