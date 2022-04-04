package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouteController {
    @Autowired
    final private AdminService adminService;


    public RouteController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("admin/route-list")
    public String getRoutes(Model model)
    {
        adminService.getRoutes(model);
        return "route/route-list";
    }

    @GetMapping("admin/route-edit/{id}")
    public String editRoute( Model model){


        return "route/route-edit/";
    }

    @GetMapping("admin/route-add")
    public String addRoute(Model model){
        model.addAttribute("message","");
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

}
