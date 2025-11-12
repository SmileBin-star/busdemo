package com.example.demo.dao;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

/**
 * 线路-站点关联响应DTO - 包含站点详情
 */
@Data
public class BusRouteStationRespDTO {
    private Integer id; // 关联ID
    private Integer stationId; // 站点ID
    private String stationName; // 站点名称（关联查询）
    private Integer stationOrder; // 站点顺序
    private BigDecimal distanceFromPrev; // 距前一站距离（公里）
    private Integer estimatedTime; // 预计通过时间（分钟）
    private String stationTypeDesc; // 站点类型描述（1-普通站，2-枢纽站）

    // 站点类型描述转换（前端直接使用）
    public String getStationTypeDesc() {
        return stationType == 2 ? "枢纽站" : "普通站";
    }

    // 站点类型（从bus_stations表关联获取，临时存储）
    private Integer stationType;
}