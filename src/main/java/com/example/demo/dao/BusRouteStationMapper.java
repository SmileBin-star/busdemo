package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BusRouteStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 线路-站点关联数据访问接口
 */
@Mapper
public interface BusRouteStationMapper extends BaseMapper<BusRouteStation> {
    /**
     * 根据线路ID查询关联的站点列表（按站点顺序排序）
     * @param routeId 线路ID
     * @return 线路-站点关联列表
     */
    List<BusRouteStation> selectByRouteId(@Param("routeId") Integer routeId);

    /**
     * 批量插入线路-站点关联数据（新增线路时用）
     * @param list 线路-站点关联列表
     * @return 插入成功条数
     */
    int batchInsert(@Param("list") List<BusRouteStation> list);

    /**
     * 根据线路ID删除所有关联的站点（删除线路/更新线路站点时用）
     * @param routeId 线路ID
     * @return 删除成功条数
     */
    int deleteByRouteId(@Param("routeId") Integer routeId);
}