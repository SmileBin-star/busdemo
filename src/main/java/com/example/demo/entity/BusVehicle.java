package com.example.demo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.Date;

@Data
public class BusVehicle {
    private Integer vehicleId;

    @NotBlank(message = "车牌号不能为空")
    private String licensePlate;

    @NotBlank(message = "车型不能为空")
    private String model;

    @NotNull(message = "载客量不能为空")
    @Positive(message = "载客量必须为正数")
    private Integer capacity;

    private Integer status;
    private Date purchaseDate;
    private Date createTime;
}