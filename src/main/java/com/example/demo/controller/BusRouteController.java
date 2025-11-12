package com.example.demo.controller;

import com.example.demo.dao.BusRouteAddDTO;
import com.example.demo.dao.BusRouteUpdateDTO;
import com.example.demo.service.BusRouteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 公交线路接口 - 支持管理员增删改查，普通用户仅查询
 */
@RestController
@RequestMapping("/api/routes")
@Validated
public class BusRouteController {

    @Resource
    private BusRouteService busRouteService;

    /**
     * 新增线路（管理员）- 含关联站点
     */
    @PostMapping
    public Result<?> addRoute(@RequestBody @Valid BusRouteAddDTO dto) {
        boolean success = busRouteService.addRoute(dto);
        return success ? Result.success("线路新增成功") : Result.error("线路新增失败");
    }

    /**
     * 修改线路（管理员）- 不含站点，站点单独维护
     */
    @PutMapping
    public Result<?> updateRoute(@RequestBody @Valid BusRouteUpdateDTO dto) {
        boolean success = busRouteService.updateRoute(dto);
        return success ? Result.success("线路修改成功") : Result.error("线路修改失败");
    }

    /**
     * 删除线路（管理员）- 级联删除关联站点
     */
    @DeleteMapping("/{routeId}")
    public Result<?> deleteRoute(@PathVariable @Positive(message = "线路ID必须为正整数") Integer routeId) {
        boolean success = busRouteService.deleteRoute(routeId);
        return success ? Result.success("线路删除成功") : Result.error("线路删除失败");
    }

    /**
     * 多条件查询线路（普通用户/管理员）
     * @param routeName 线路名称（模糊）
     * @param startStation 起点站ID
     * @param endStation 终点站ID
     * @param status 线路状态（0-停运/1-运营）
     * @return 线路列表（含起点/终点名称）
     */
    @GetMapping
    public Result<List<BusRouteRespDTO>> queryRoutes(
            @RequestParam(required = false) String routeName,
            @RequestParam(required = false) Integer startStation,
            @RequestParam(required = false) Integer endStation,
            @RequestParam(required = false) Integer status
    ) {
        List<BusRouteRespDTO> routeList = busRouteService.queryByCondition(routeName, startStation, endStation, status);
        return Result.success(routeList);
    }

    /**
     * 根据ID查询线路详情（普通用户/管理员）- 含关联站点列表
     */
    @GetMapping("/{routeId}")
    public Result<BusRouteRespDTO> getRouteDetail(@PathVariable @Positive(message = "线路ID必须为正整数") Integer routeId) {
        BusRouteRespDTO routeDetail = busRouteService.getRouteDetail(routeId);
        return routeDetail != null ? Result.success(routeDetail) : Result.error("线路不存在");
    }
}