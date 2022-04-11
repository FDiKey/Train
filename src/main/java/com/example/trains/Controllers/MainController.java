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
    final private StationRepo stationRepo;

    @Autowired
    final private SearchService searchService;

    @Autowired
    final private TicketService ticketService;

    public MainController(StationRepo stationRepo, SearchService searchService, TicketService ticketService) {
        this.stationRepo = stationRepo;
        this.searchService = searchService;
        this.ticketService = ticketService;
    }


    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "") String stationFrom,
                        @RequestParam(required = false, defaultValue = "") String stationTo,  Model model){
        Iterable<Schedule> schedules;

        if((!stationFrom.isEmpty() && !stationTo.isEmpty())) {
            searchService.getScheduleFromToStation(stationFrom, stationTo, model);
        }
        else {
            searchService.getMainPage(model);
        }
        return "index";
    }

    @GetMapping("my-tickets")
    public String myTickets(@AuthenticationPrincipal User user, Model model){
        ticketService.showTicketsByUser(user, model);
        return "my-tickets";
    }

}
