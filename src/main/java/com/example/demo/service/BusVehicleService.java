package com.example.demo.service;

import com.example.demo.entity.BusVehicle;
import com.example.demo.vo.PageInfo;
import java.util.List;

public interface BusVehicleService {
    List<BusVehicle> findAll();
    BusVehicle findById(Integer id);
    boolean save(BusVehicle vehicle);
    boolean delete(Integer id);
    // 添加分页查询
    PageInfo<BusVehicle> findByPage(Integer pageNum, Integer pageSize);
}