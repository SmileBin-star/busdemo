package com.example.demo.entity;

import lombok.Data;
import java.util.Date;

@Data
public class BusArrivalRecord {
    private Integer recordId;          // 记录ID
    private Integer scheduleId;        // 关联时刻表ID
    private String stationName;        // 站点名称
    private Date actualArrivalTime;    // 实际到站时间
    private Integer delayMinutes;      // 延迟分钟数
    private Date createTime;           // 创建时间
    private BusSchedule schedule;      // 关联时刻表信息
}