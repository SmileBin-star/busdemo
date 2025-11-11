package com.example.demo.service;

import com.example.demo.entity.BusVehicle;
import java.util.List;

public interface BusVehicleService {
    List<BusVehicle> findAll();
    BusVehicle findById(Integer id);
    boolean save(BusVehicle vehicle);
    boolean delete(Integer id);
}