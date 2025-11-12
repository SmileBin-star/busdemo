package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BusRouteStationMapper;
import com.example.demo.entity.BusRouteStation;
import com.example.demo.service.BusRouteStationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BusRouteStationServiceImpl extends ServiceImpl<BusRouteStationMapper, BusRouteStation> implements BusRouteStationService {

    @Resource
    private BusRouteStationMapper busRouteStationMapper;

    @Override
    public List<BusRouteStation> getByRouteId(Integer routeId) {
        return busRouteStationMapper.selectByRouteId(routeId);
    }

    @Override
    public boolean batchAdd(List<BusRouteStation> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return busRouteStationMapper.batchInsert(list) == list.size();
    }

    @Override
    public boolean deleteAllByRouteId(Integer routeId) {
        return busRouteStationMapper.deleteByRouteId(routeId) > 0;
    }
}