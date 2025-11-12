package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@TableName("bus_routes")
public class BusRoute {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer routeId; // 线路ID（PK）
    private String routeName; // 线路名称
    private Integer startStation; // 起点站ID（FK -> bus_stations.station_id）
    private Integer endStation; // 终点站ID（FK -> bus_stations.station_id）
    private Integer totalStations; // 总站点数
    private BigDecimal totalDistance; // 总里程（公里）
    private LocalTime departureTime; // 首班车时间
    private LocalTime arrivalTime; // 末班车时间
    private Integer status; // 状态（0-停运/1-运营）
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}