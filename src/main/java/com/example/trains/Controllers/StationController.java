package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.domain.Route;
import com.example.trains.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StationController {
    @Autowired
    private AdminService adminService;

    @GetMapping("admin/station-list")
    public String stationList(Model model)
    {
        adminService.getAllStations(model);
        return "station/station-list";
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


    @GetMapping("admin/station-add")
    public String stationAdd(){
        return "station/station-add";
    }



    @GetMapping("admin/station-edit/{station}")
    public String editStation(@PathVariable Station station, Model model){
        model.addAttribute("station", station);
        return "station/station-edit";
    }

    @PostMapping("admin/station-edit/{station}")
    public String updateRoute(@PathVariable Station station,
                              @RequestParam String stationName,
                              //Add previous and next stations
                              Model model){
        adminService.updateStation(station, stationName, model);
        return "redirect:/admin/station-list";
    }

    @GetMapping("admin/station-delete/{station}")
    public String deleteStation(Station station, Model model){
        adminService.deleteStation(station);
        return "redirect:/admin/station-list";
    }

}
