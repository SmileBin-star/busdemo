package com.example.demo.controller;

import com.example.demo.entity.BusVehicle;
import com.example.demo.service.BusVehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehicle")
public class BusVehicleController {

    @Autowired
    private BusVehicleService vehicleService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        // 添加分页功能
        model.addAttribute("pageInfo", vehicleService.findByPage(pageNum, pageSize));
        return "vehicle/list";
    }

    @GetMapping("/form")
    public String toForm(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            BusVehicle vehicle = vehicleService.findById(id);
            if (vehicle == null) {
                throw new IllegalArgumentException("车辆不存在");
            }
            model.addAttribute("vehicle", vehicle);
        } else {
            model.addAttribute("vehicle", new BusVehicle()); // 初始化空对象，避免页面报错
        }
        return "vehicle/form";
    }

    @PostMapping("/save")
    public String save(@Valid BusVehicle vehicle, BindingResult result, RedirectAttributes redirect) {
        // 添加参数校验
        if (result.hasErrors()) {
            return "vehicle/form"; // 校验失败返回表单页
        }
        boolean success = vehicleService.save(vehicle);
        redirect.addFlashAttribute("msg", success ? "操作成功" : "操作失败");
        return "redirect:/vehicle/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes redirect) {
        // 添加参数校验
        if (id == null || id <= 0) {
            redirect.addFlashAttribute("msg", "参数错误：车辆ID无效");
            return "redirect:/vehicle/list";
        }
        boolean success = vehicleService.delete(id);
        redirect.addFlashAttribute("msg", success ? "删除成功" : "删除失败");
        return "redirect:/vehicle/list";
    }
}