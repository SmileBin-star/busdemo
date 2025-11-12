package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BusRouteMapper;
import com.example.demo.dao.BusRouteStationMapper;
import com.example.demo.service.BusRouteService;
import com.example.demo.service.BusStationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公交线路业务实现
 */
@Service
class BusRouteServiceImpl extends ServiceImpl<BusRouteMapper, BusRoute> implements BusRouteService {

    @Resource
    private BusRouteMapper busRouteMapper;

    @Resource
    private BusRouteStationMapper busRouteStationMapper;

    @Resource
    private BusStationService busStationService;

    /**
     * 新增线路：事务保证线路和关联站点同时成功/失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRoute(BusRouteAddDTO dto) {
        // 1. 转换DTO为线路实体，保存线路基本信息
        BusRoute route = new BusRoute();
        route.setRouteName(dto.getRouteName());
        route.setStartStation(dto.getStartStation());
        route.setEndStation(dto.getEndStation());
        route.setTotalStations(dto.getTotalStations());
        route.setTotalDistance(dto.getTotalDistance());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setStatus(1); // 默认创建后为运营状态
        boolean routeSaved = save(route);
        if (!routeSaved) {
            throw new RuntimeException("线路基本信息保存失败");
        }

        // 2. 转换线路-站点DTO为实体，批量保存关联关系
        List<BusRouteStation> stationList = dto.getStationList().stream().map(item -> {
            BusRouteStation routeStation = new BusRouteStation();
            routeStation.setRouteId(route.getRouteId()); // 关联新增的线路ID
            routeStation.setStationId(item.getStationId());
            routeStation.setStationOrder(item.getStationOrder());
            routeStation.setDistanceFromPrev(item.getDistanceFromPrev());
            routeStation.setEstimatedTime(item.getEstimatedTime());
            return routeStation;
        }).collect(Collectors.toList());

        // 批量插入关联数据
        int batchInsertCount = busRouteStationMapper.batchInsert(stationList);
        if (batchInsertCount != stationList.size()) {
            throw new RuntimeException("线路-站点关联保存失败");
        }

        return true;
    }

    @Override
    public boolean updateRoute(BusRouteUpdateDTO dto) {
        // 验证线路是否存在
        if (!existsById(dto.getRouteId())) {
            return false;
        }

        // 转换DTO为实体，更新线路信息
        BusRoute route = new BusRoute();
        route.setRouteId(dto.getRouteId());
        route.setRouteName(dto.getRouteName());
        route.setStartStation(dto.getStartStation());
        route.setEndStation(dto.getEndStation());
        route.setTotalStations(dto.getTotalStations());
        route.setTotalDistance(dto.getTotalDistance());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
        route.setStatus(dto.getStatus());
        return updateById(route);
    }

    @Override
    public List<BusRouteRespDTO> queryByCondition(String routeName, Integer startStation, Integer endStation, Integer status) {
        // 模糊查询处理
        routeName = routeName != null ? "%" + routeName + "%" : null;
        List<BusRoute> routeList = busRouteMapper.selectByCondition(routeName, startStation, endStation, status);

        // 转换为响应DTO（关联查询起点站/终点站名称）
        return routeList.stream().map(route -> {
            BusRouteRespDTO resp = new BusRouteRespDTO();
            resp.setRouteId(route.getRouteId());
            resp.setRouteName(route.getRouteName());
            resp.setStartStation(route.getStartStation());
            resp.setEndStation(route.getEndStation());
            resp.setTotalStations(route.getTotalStations());
            resp.setTotalDistance(route.getTotalDistance());
            resp.setDepartureTime(route.getDepartureTime());
            resp.setArrivalTime(route.getArrivalTime());
            resp.setStatus(route.getStatus());

            // 关联查询起点站名称
            BusStation startStationObj = busStationService.getById(route.getStartStation());
            resp.setStartStationName(startStationObj != null ? startStationObj.getStationName() : "未知站点");

            // 关联查询终点站名称
            BusStation endStationObj = busStationService.getById(route.getEndStation());
            resp.setEndStationName(endStationObj != null ? endStationObj.getStationName() : "未知站点");

            return resp;
        }).collect(Collectors.toList());
    }

    @Override
    public BusRouteRespDTO getRouteDetail(Integer routeId) {
        // 1. 查询线路基本信息
        BusRoute route = getById(routeId);
        if (route == null) {
            return null;
        }

        // 2. 转换为响应DTO
        BusRouteRespDTO resp = new BusRouteRespDTO();
        resp.setRouteId(route.getRouteId());
        resp.setRouteName(route.getRouteName());
        resp.setStartStation(route.getStartStation());
        resp.setEndStation(route.getEndStation());
        resp.setTotalStations(route.getTotalStations());
        resp.setTotalDistance(route.getTotalDistance());
        resp.setDepartureTime(route.getDepartureTime());
        resp.setArrivalTime(route.getArrivalTime());
        resp.setStatus(route.getStatus());

        // 3. 关联查询起点站/终点站名称
        BusStation startStationObj = busStationService.getById(route.getStartStation());
        BusStation endStationObj = busStationService.getById(route.getEndStation());
        resp.setStartStationName(startStationObj != null ? startStationObj.getStationName() : "未知站点");
        resp.setEndStationName(endStationObj != null ? endStationObj.getStationName() : "未知站点");

        // 4. 查询关联的站点列表（按顺序排序）
        List<BusRouteStation> routeStationList = busRouteStationMapper.selectByRouteId(routeId);
        List<BusRouteStationRespDTO> stationRespList = routeStationList.stream().map(routeStation -> {
            BusRouteStationRespDTO stationResp = new BusRouteStationRespDTO();
            stationResp.setId(routeStation.getId());
            stationResp.setStationId(routeStation.getStationId());
            stationResp.setStationOrder(routeStation.getStationOrder());
            stationResp.setDistanceFromPrev(routeStation.getDistanceFromPrev());
            stationResp.setEstimatedTime(routeStation.getEstimatedTime());

            // 关联查询站点名称和类型
            BusStation station = busStationService.getById(routeStation.getStationId());
            if (station != null) {
                stationResp.setStationName(station.getStationName());
                stationResp.setStationType(station.getStationType());
            } else {
                stationResp.setStationName("未知站点");
                stationResp.setStationType(1); // 默认普通站
            }
            return stationResp;
        }).collect(Collectors.toList());
        resp.setStationList(stationRespList);

        return resp;
    }

    /**
     * 删除线路：级联删除关联的站点（事务保证）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoute(Integer routeId) {
        // 1. 删除线路关联的站点
        busRouteStationMapper.deleteByRouteId(routeId);
        // 2. 删除线路本身
        return removeById(routeId);
    }
}