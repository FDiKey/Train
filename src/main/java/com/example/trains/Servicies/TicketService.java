package com.example.trains.Servicies;

import com.example.trains.Repo.TicketRepo;
import com.example.trains.domain.Station;
import com.example.trains.domain.Ticket;
import com.example.trains.domain.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    public boolean add(long trainId, Station from, Station to, double price, int seat )
    {
//        Ticket ticket = new Ticket(trainId, from, to, price, seat);
        return true;
    }

    public boolean update(long trainId, Station from, Station to, double price, int seat )
    {

        return true;
    }

    public void createTickets(Train byTrainNumber, String seatCount) {
        Ticket ticket;
        for(int i = 1; i <= Integer.valueOf(seatCount); i++){
            ticket = new Ticket(byTrainNumber, i);
            ticketRepo.save(ticket);
        }
    }


}
