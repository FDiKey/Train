package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StationController {
    @Autowired
    final private AdminService adminService;

    public StationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("admin/station-list")
    public String stationList(Model model)
    {
        adminService.getAllStations(model);
        return "station/station-list";
    }

    @GetMapping("stations/station-edit")
    public String editStation(Station station, Model model)
    {
        return "station/station-edit";
    }

    @PostMapping("admin/station-add")
    public String add(@RequestParam String stationName,
                      @RequestParam String route,
                      @RequestParam String previous,
                      @RequestParam String next,
                      Model model){
        if(!adminService.addStaion(stationName, route, previous, next))
        {
            model.addAttribute("message", "This station allready exists");
            return "station/station-list";
        }
        return "redirect:/admin/station-list";
    }


    // to-do!!!
    @GetMapping("admin/station-edit/{id}")
    public String stationEdit(Station station, Model model){

        return "station/station-edit/{"+station.getId() + "}";
    }

    @GetMapping("admin/station-add")
    public String stationAdd(){
        return "station/station-add";
    }


}
