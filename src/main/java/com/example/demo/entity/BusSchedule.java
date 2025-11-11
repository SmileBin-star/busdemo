package com.example.demo.entity;

import lombok.Data;
import java.sql.Time;

@Data
public class BusSchedule {
    private Integer scheduleId;        // 时刻表ID
    private String routeName;          // 线路名称
    private Integer vehicleId;         // 关联车辆ID
    private Time departureTime;        // 发车时间
    private Time arrivalTime;          // 到达时间
    private Integer stationCount;      // 站点数量
    private Integer status;            // 状态(1:有效,0:无效)
    private BusVehicle vehicle;        // 关联车辆信息
}