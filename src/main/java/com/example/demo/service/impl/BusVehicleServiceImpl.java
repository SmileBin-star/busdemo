package com.example.demo.service.impl;

import com.example.demo.dao.BusVehicleMapper;
import com.example.demo.entity.BusVehicle;
import com.example.demo.service.BusVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusVehicleServiceImpl implements BusVehicleService {

    @Autowired
    private BusVehicleMapper vehicleMapper;

    @Override
    public List<BusVehicle> findAll() {
        return vehicleMapper.selectAll();
    }

    @Override
    public BusVehicle findById(Integer id) {
        return vehicleMapper.selectById(id);
    }

    @Override
    public boolean save(BusVehicle vehicle) {
        if (vehicle.getVehicleId() == null) {
            vehicle.setCreateTime(new Date());
            return vehicleMapper.insert(vehicle) > 0;
        } else {
            return vehicleMapper.update(vehicle) > 0;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return vehicleMapper.deleteById(id) > 0;
    }
}