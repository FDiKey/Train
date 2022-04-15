package com.example.trains.Controllers;

import com.example.trains.Repo.StationRepo;
import com.example.trains.Servicies.SearchService;
import com.example.trains.Servicies.TicketService;
import com.example.trains.domain.Schedule;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private StationRepo stationRepo;

    @Autowired
    private SearchService searchService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "") String stationFrom,
                        @RequestParam(required = false, defaultValue = "") String stationTo,  Model model){

        var stationList = searchService.getMainPage(model);
        model.addAttribute("stationByRoute", stationList);
        if((!stationFrom.isEmpty() && !stationTo.isEmpty())) {
            searchService.setScheduleFromByStation(stationFrom, stationTo, model);
        }

        return "index";
    }

    @GetMapping("my-tickets")
    public String myTickets(@AuthenticationPrincipal User user, Model model){
        ticketService.showTicketsByUser(user, model);
        return "my-tickets";
    }

}
