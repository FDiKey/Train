package com.example.trains.Controllers;

import com.example.trains.DTO.RouteDTO.RouteEditDTO;
import com.example.trains.DTO.RouteDTO.RouteFromDTO;
import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class RouteController {
    @Autowired
    private AdminService adminService;

    @GetMapping("admin/route-list")
    public String getRoutes(Model model){
        var dtos = adminService.getAllRoutes();

        model.addAttribute("routes", dtos);
        return "route/route-list";
    }

    @GetMapping("admin/route-edit/{route}")
    public String editRoute(@PathVariable Route route, Model model){

        var dto = adminService.setStationNamesByRoute(route);

        model.addAttribute("route", dto);
        return "route/route-edit";
    }

    @PostMapping("admin/route-edit/{route}")
    public String updateRoute(@PathVariable Route route,
                              @RequestParam String routeNumber,
                              Model model){
        adminService.isRouteUpdated(route, routeNumber, model);
        return "redirect:/admin/route-list/";
    }

    @GetMapping("admin/route-add")
    public String addRoute(){
        return "route/route-add";
    }

    @PostMapping("admin/route-add")
    public String addRoute(@RequestParam String routeNumber, Model model)
    {

        if(!adminService.addRoute(routeNumber))
        {
            model.addAttribute("message", "route with this number already exists");
            return "route/route-add";
        }
        return "redirect:/admin/route-list";
    }

    @GetMapping("admin/route-delede/{route}")
    public String deleteRoute(@PathVariable Route route){
        adminService.deleteRoute(route);
        return "redirect:/admin/route-list";
    }


}
