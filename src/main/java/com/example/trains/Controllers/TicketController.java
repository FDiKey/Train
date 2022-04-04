package com.example.trains.Controllers;

import com.example.trains.Servicies.AdminService;
import com.example.trains.Servicies.TicketService;
import com.example.trains.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("admin/ticket-list/")
    public String getTrainTickets(@RequestParam String trainNumber, Model model)
    {
        if(trainNumber != null) {
            Iterable<Ticket> tickets = adminService.getAllTickets(trainNumber);
            model.addAttribute("tickets", tickets);
        }else{
            model.addAttribute("message","no tickets on train");
        }


        return "ticket/ticket-list";
    }
    @GetMapping("admin/ticket-list")
    public String getTickets( Model model) {
        adminService.getAllTrains(model);
        return "ticket/ticket-list";
    }
}
