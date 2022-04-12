package com.example.trains.Controllers;

import com.example.trains.Servicies.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/admin/refresh-schedule")
    public String refresh()
    {
        scheduleService.refreshAllSchedule();
        return "redirect:/admin/";
    }

}
