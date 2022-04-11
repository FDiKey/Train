package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.Servicies.TicketService;
import com.example.trains.domain.Schedule;
import com.example.trains.domain.Ticket;
import com.example.trains.domain.Train;
import com.example.trains.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    @Autowired
    final private TicketService ticketService;
    @Autowired
    final private AdminService adminService;

    public TicketController(TicketService ticketService, AdminService adminService) {
        this.ticketService = ticketService;
        this.adminService = adminService;
    }

    @GetMapping("admin/ticket-list")
    public String getTrainTickets(@RequestParam(required = false) String trainNumber, Model model)
    {
        if(trainNumber != null) {
            Iterable<Ticket> tickets = adminService.getAllTickets(trainNumber);
            model.addAttribute("tickets", tickets);
        }
        adminService.getAllTrains(model);
        return "ticket/ticket-list";
    }

    @GetMapping("ticket-buy/{scheduleFrom}/{scheduleTo}")
    public String ticketFrom(@PathVariable Schedule scheduleFrom, @PathVariable Schedule scheduleTo, Model model)
    {
        ticketService.showTickets(scheduleFrom, scheduleTo, model);

        return "ticket/ticket-buy";
    }

    @PostMapping("ticket-buy/{scheduleFrom}/{scheduleTo}/{seatNumber}")
    public String buyTicket(@AuthenticationPrincipal User user,
                            @PathVariable Schedule scheduleFrom,
                            @PathVariable Schedule scheduleTo,
                            @PathVariable String seatNumber,
                            Model model){
        ticketService.buyTicket(user, scheduleFrom.getTrain(), scheduleFrom, scheduleTo, Integer.valueOf(seatNumber));
        return "redirect:/my-tickets/";
    }

}
