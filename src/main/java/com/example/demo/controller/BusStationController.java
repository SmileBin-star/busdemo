package com.example.demo.controller;

import com.example.demo.entity.BusStation;
import com.example.demo.service.BusStationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 公交站点接口 - 支持管理员增删改查，普通用户仅查询
 */
@RestController
@RequestMapping("/api/stations")
@Validated
public class BusStationController {

    @Resource
    private BusStationService busStationService;

    /**
     * 新增站点（管理员）
     */
    @PostMapping
    public Result<?> addStation(@RequestBody @Valid BusStation station) {
        boolean success = busStationService.save(station);
        return success ? Result.success("站点新增成功") : Result.error("站点新增失败");
    }

    /**
     * 修改站点（管理员）
     */
    @PutMapping("/{stationId}")
    public Result<?> updateStation(
            @PathVariable @Positive(message = "站点ID必须为正整数") Integer stationId,
            @RequestBody @Valid BusStation station
    ) {
        station.setStationId(stationId);
        boolean success = busStationService.updateById(station);
        return success ? Result.success("站点修改成功") : Result.error("站点修改失败");
    }

    /**
     * 删除站点（管理员）
     */
    @DeleteMapping("/{stationId}")
    public Result<?> deleteStation(@PathVariable @Positive(message = "站点ID必须为正整数") Integer stationId) {
        boolean success = busStationService.removeById(stationId);
        return success ? Result.success("站点删除成功") : Result.error("站点删除失败");
    }

    /**
     * 多条件查询站点（普通用户/管理员）
     * @param stationName 站点名称（模糊）
     * @param address 站点地址（模糊）
     * @param stationType 站点类型（1-普通站/2-枢纽站）
     * @return 站点列表
     */
    @GetMapping
    public Result<List<BusStation>> queryStations(
            @RequestParam(required = false) String stationName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer stationType
    ) {
        List<BusStation> stationList = busStationService.queryByCondition(stationName, address, stationType);
        return Result.success(stationList);
    }

    /**
     * 根据ID查询站点详情（普通用户/管理员）
     */
    @GetMapping("/{stationId}")
    public Result<BusStation> getStationDetail(@PathVariable @Positive(message = "站点ID必须为正整数") Integer stationId) {
        BusStation station = busStationService.getById(stationId);
        return station != null ? Result.success(station) : Result.error("站点不存在");
    }
}