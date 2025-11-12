package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.BusStation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 公交站点业务接口
 */
public interface BusStationService extends IService<BusStation> {
    /**
     * 多条件查询站点
     * @param stationName 站点名称（模糊）
     * @param address 站点地址（模糊）
     * @param stationType 站点类型
     * @return 站点列表
     */
    List<BusStation> queryByCondition(String stationName, String address, Integer stationType);
}


