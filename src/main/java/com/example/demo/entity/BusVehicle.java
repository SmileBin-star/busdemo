package com.example.demo.entity;

import lombok.Data;
import java.util.Date;

@Data
public class BusVehicle {
    private Integer vehicleId;         // 车辆ID
    private String licensePlate;       // 车牌号
    private String model;              // 车型
    private Integer capacity;          // 载客量
    private Integer status;            // 状态(1:正常,0:维修)
    private Date purchaseDate;         // 购置日期
    private Date createTime;           // 创建时间
}