package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BusRouteStationMapper;
import com.example.demo.entity.BusRouteStation;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 线路-站点关联业务接口
 */
public interface BusRouteStationService extends IService<BusRouteStation> {
    /**
     * 根据线路ID查询关联站点（按顺序）
     * @param routeId 线路ID
     * @return 关联站点列表
     */
    List<BusRouteStation> getByRouteId(Integer routeId);

    /**
     * 批量新增线路-站点关联
     * @param list 关联列表
     * @return true-成功，false-失败
     */
    boolean batchAdd(List<BusRouteStation> list);

    /**
     * 根据线路ID删除所有关联站点
     * @param routeId 线路ID
     * @return true-成功，false-失败
     */
    boolean deleteAllByRouteId(Integer routeId);
}

/**
 * 线路-站点关联业务实现
 */

