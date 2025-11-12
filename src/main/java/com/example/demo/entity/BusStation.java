package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("bus_stations")
public class BusStation {
    @TableId(type = IdType.AUTO) // 主键自增
    private Integer stationId; // 站点ID（PK）
    private String stationName; // 站点名称
    private BigDecimal latitude; // 纬度
    private BigDecimal longitude; // 经度
    private String address; // 站点地址
    private Integer stationType; // 站点类型（1-普通站/2-枢纽站）
    private Integer status; // 状态（0-关闭/1-启用）
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}