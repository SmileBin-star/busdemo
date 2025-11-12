package com.example.demo.dao;

import com.example.demo.entity.BusVehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BusVehicleMapper {
    List<BusVehicle> selectAll();
    BusVehicle selectById(Integer vehicleId);
    int insert(BusVehicle vehicle);
    int update(BusVehicle vehicle);
    int deleteById(Integer vehicleId);
    // 分页查询
    List<BusVehicle> selectByPage(@Param("start") Integer start, @Param("size") Integer size);
    // 总条数
    int countTotal();
}