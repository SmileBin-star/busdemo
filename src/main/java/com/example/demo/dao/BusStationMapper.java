package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BusStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 公交站点数据访问接口
 */
@Mapper
public interface BusStationMapper extends BaseMapper<BusStation> {
    /**
     * 多条件查询站点（支持名称模糊、地址模糊、类型筛选）
     * @param stationName 站点名称（模糊匹配）
     * @param address 站点地址（模糊匹配）
     * @param stationType 站点类型（1-普通站/2-枢纽站）
     * @return 站点列表
     */
    List<BusStation> selectByCondition(
            @Param("stationName") String stationName,
            @Param("address") String address,
            @Param("stationType") Integer stationType
    );
}