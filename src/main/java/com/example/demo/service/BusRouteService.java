package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公交线路业务接口
 */
public interface BusRouteService extends IService<BusRoute> {
    /**
     * 新增线路（含关联站点，事务保证一致性）
     * @param dto 新增线路DTO
     * @return true-成功，false-失败
     */
    boolean addRoute(BusRouteAddDTO dto);

    /**
     * 修改线路（不含站点，站点单独维护）
     * @param dto 修改线路DTO
     * @return true-成功，false-失败
     */
    boolean updateRoute(BusRouteUpdateDTO dto);

    /**
     * 多条件查询线路
     * @param routeName 线路名称（模糊）
     * @param startStation 起点站ID
     * @param endStation 终点站ID
     * @param status 线路状态
     * @return 线路响应DTO列表
     */
    List<BusRouteRespDTO> queryByCondition(String routeName, Integer startStation, Integer endStation, Integer status);

    /**
     * 根据ID查询线路详情（含关联站点）
     * @param routeId 线路ID
     * @return 线路详情响应DTO
     */
    BusRouteRespDTO getRouteDetail(Integer routeId);

    /**
     * 删除线路（级联删除关联站点）
     * @param routeId 线路ID
     * @return true-成功，false-失败
     */
    boolean deleteRoute(Integer routeId);
}

