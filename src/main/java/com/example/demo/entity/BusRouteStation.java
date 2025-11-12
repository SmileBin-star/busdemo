package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("bus_route_stations")
public class BusRouteStation {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id; // 关联ID（PK）
    private Integer routeId; // 线路ID（FK -> bus_routes.route_id）
    private Integer stationId; // 站点ID（FK -> bus_stations.station_id）
    private Integer stationOrder; // 站点顺序
    private BigDecimal distanceFromPrev; // 距前一站距离（公里）
    private Integer estimatedTime; // 预计通过时间（分钟）
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}