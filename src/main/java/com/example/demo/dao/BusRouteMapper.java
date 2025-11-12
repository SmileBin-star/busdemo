package com.example.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.BusRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 公交线路数据访问接口
 */
@Mapper
public interface BusRouteMapper extends BaseMapper<BusRoute> {
    /**
     * 多条件查询线路（支持名称模糊、起点/终点、状态筛选）
     * @param routeName 线路名称（模糊匹配）
     * @param startStation 起点站ID
     * @param endStation 终点站ID
     * @param status 线路状态（0-停运/1-运营）
     * @return 线路列表
     */
    List<BusRoute> selectByCondition(
            @Param("routeName") String routeName,
            @Param("startStation") Integer startStation,
            @Param("endStation") Integer endStation,
            @Param("status") Integer status
    );

    /**
     * 根据线路ID查询线路及关联的站点信息（用于详情查询）
     * @param routeId 线路ID
     * @return 线路实体（含关联站点）
     */
    BusRoute selectRouteWithStations(@Param("routeId") Integer routeId);

    @Override
    int insert(BusRoute entity);

    @Override
    int deleteById(Serializable id);

    @Override
    int deleteById(BusRoute entity);

    @Override
    int delete(Wrapper<BusRoute> queryWrapper);

    @Override
    int deleteBatchIds(Collection<?> idList);

    @Override
    int updateById(BusRoute entity);

    @Override
    int update(BusRoute entity, Wrapper<BusRoute> updateWrapper);

    @Override
    BusRoute selectById(Serializable id);

    @Override
    List<BusRoute> selectBatchIds(Collection<? extends Serializable> idList);

    @Override
    void selectBatchIds(Collection<? extends Serializable> idList, ResultHandler<BusRoute> resultHandler);

    @Override
    Long selectCount(Wrapper<BusRoute> queryWrapper);

    @Override
    List<BusRoute> selectList(Wrapper<BusRoute> queryWrapper);

    @Override
    void selectList(Wrapper<BusRoute> queryWrapper, ResultHandler<BusRoute> resultHandler);

    @Override
    List<BusRoute> selectList(IPage<BusRoute> page, Wrapper<BusRoute> queryWrapper);

    @Override
    void selectList(IPage<BusRoute> page, Wrapper<BusRoute> queryWrapper, ResultHandler<BusRoute> resultHandler);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<BusRoute> queryWrapper);

    @Override
    void selectMaps(Wrapper<BusRoute> queryWrapper, ResultHandler<Map<String, Object>> resultHandler);

    @Override
    List<Map<String, Object>> selectMaps(IPage<? extends Map<String, Object>> page, Wrapper<BusRoute> queryWrapper);

    @Override
    void selectMaps(IPage<? extends Map<String, Object>> page, Wrapper<BusRoute> queryWrapper, ResultHandler<Map<String, Object>> resultHandler);

    @Override
    <E> List<E> selectObjs(Wrapper<BusRoute> queryWrapper);

    @Override
    <E> void selectObjs(Wrapper<BusRoute> queryWrapper, ResultHandler<E> resultHandler);
}