package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Route;
import com.example.trains.domain.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam String timeStart,
            Model model
            ){
        if(!adminService.addTrain(trainNumber, route, seatCount, dateStart, timeStart)) {
            model.addAttribute("message", "wrong train number");
            return "train/train-add";
        }
        return "redirect:/admin/train-list";
    }


    @GetMapping("admin/train-edit/{train}")
    public String editTrain(@PathVariable Train train,
                            Model model)    {
        model.addAttribute("routes", adminService.getAllRoutes());
        return "train/train-edit";
    }


    @PostMapping("admin/train-edit/{train}")
    public String updateTrain(@PathVariable Train train,
                              @RequestParam String trainNumber,
                              @RequestParam String route,
                              @RequestParam String seatCount,
                              @RequestParam String dateStart,
                              Model model)
    {
        if(adminService.checkUpdate(train, trainNumber, route, seatCount, LocalDateTime.parse(dateStart)))
        {
            return "redirect:/admin/train-list";
        }
        model.addAttribute("routes", adminService.getAllRoutes());
        return "train/train-edit";
    }

    @GetMapping("admin/train-delete/{train}")
    public String deleteTrain(Train train, Model model)
    {
        adminService.deleteTrain(train);
        return "redirect:/admin/train-list";
    }
}
