package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class TrainController {
    @Autowired
    final private AdminService adminService;

    public TrainController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("admin/train-list")
    public String trainList(Model model)
    {
        adminService.getAllTrains(model);
        return "/train/train-list";
    }

    @GetMapping("admin/train-add")
    public String getTrain(Model model){
        Iterable<Route> routes = adminService.getAllRoutes();

        model.addAttribute("message", "");
        model.addAttribute("routes", routes);
        return "train/train-add";
    }

    @PostMapping("admin/train-add")
    public String trainAdd(
            @RequestParam String trainNumber,
            @RequestParam String route,
            @RequestParam String seatCount,
            @RequestParam String dateStart,
            Model model
            ){
        if(!adminService.addTrain(trainNumber, route, seatCount, dateStart)) {
            model.addAttribute("message", "wrong train number");
            return "train/train-add";
        }
        return "redirect:/admin/train-list";
    }
}
