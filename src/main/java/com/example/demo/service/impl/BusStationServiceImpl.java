package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BusStationMapper;
import com.example.demo.entity.BusStation;
import com.example.demo.service.BusStationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BusStationServiceImpl extends ServiceImpl<BusStationMapper, BusStation> implements BusStationService {

    @Resource
    private BusStationMapper busStationMapper;

    @Override
    public List<BusStation> queryByCondition(String stationName, String address, Integer stationType) {
        // 模糊查询处理：前端传参为空则不参与筛选
        stationName = stationName != null ? "%" + stationName + "%" : null;
        address = address != null ? "%" + address + "%" : null;
        return busStationMapper.selectByCondition(stationName, address, stationType);
    }
}