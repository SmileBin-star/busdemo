package com.example.demo.controller;

import com.example.demo.entity.BusVehicle;
import com.example.demo.service.BusVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehicle")
public class BusVehicleController {

    @Autowired
    private BusVehicleService vehicleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "vehicle/list";
    }

    @GetMapping("/form")
    public String toForm(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("vehicle", vehicleService.findById(id));
        }
        return "vehicle/form";
    }

    @PostMapping("/save")
    public String save(BusVehicle vehicle, RedirectAttributes redirect) {
        boolean success = vehicleService.save(vehicle);
        redirect.addFlashAttribute("msg", success ? "操作成功" : "操作失败");
        return "redirect:/vehicle/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes redirect) {
        boolean success = vehicleService.delete(id);
        redirect.addFlashAttribute("msg", success ? "删除成功" : "删除失败");
        return "redirect:/vehicle/list";
    }
}