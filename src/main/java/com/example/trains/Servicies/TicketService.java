package com.example.trains.Servicies;

import com.example.trains.DTO.TicketDTO.TicketDTO;
import com.example.trains.DTO.TicketDTO.TicketDetailedDTO;
import com.example.trains.Repo.PassengerRepo;
import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.Repo.TicketRepo;
import com.example.trains.domain.*;
import com.example.trains.mapper.ScheduleMapper;
import com.example.trains.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private StationRepo stationRepo;

    public boolean addTicket(long trainId, Station from, Station to, double price, int seat )
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
//        ticketService.createTickets(trainRepo.findByTrainNumber(train.getTrainNumber()), seatCount);

    }

    // implementation of craetion ticket to passenger
    public Ticket buyTicket(User user, Train train, Schedule from, Schedule to, int seatNumber){
        Set<Station> between = getBetweenStations(from.getStation(), to.getStation());
        Passenger passenger = passengerRepo.findByUser(user);
        Ticket ticket = new Ticket(passenger, train, from, to, between, seatNumber);
        ticketRepo.save(ticket);
        return ticket;
    }

    private Set<Station> getBetweenStations(Station from, Station to) {
        var route = stationRepo.findStationsByRoute(from.getRoute());
        Station station = stationRepo.findStationByRouteAndPreviousStationIsNull(from.getRoute());

        boolean isBetween = false;
        do{
            if(station.equals(from) || isBetween){
                if(station.equals(to))
                    isBetween = false;
                else
                    isBetween = true;
            }else{
                route.remove(station);
            }
        }while((station = station.getNextStation()) != null);

        return route;
    }


    public void showTickets(Schedule scheduleFrom, Schedule scheduleTo, Model model) {

        Train train = scheduleFrom.getTrain();
        Station from = scheduleFrom.getStation();
        Station to = scheduleTo.getStation();
        LinkedList<Integer> seats = new LinkedList<>();
        var scheduleMapper = new ScheduleMapper();

        for(int i = 1; i <= train.getSeatCount(); i++) seats.add(i);

        Set<Station> between = getBetweenStations(from, to);

        Set<Ticket> saledTickets = ticketRepo.findAllByTrain(train);

        for(Station station : between)
        {
            for(Ticket ticket : saledTickets)
            {
                if(ticket.getStationBetween().contains(station) && seats.contains(ticket.getSeatNumber()))
                {
                    seats.removeFirstOccurrence(ticket.getSeatNumber());
                }
            }
        }

        model.addAttribute("seatCount", seats);
        model.addAttribute("price", 100 * between.stream().count());
        model.addAttribute("scheduleFrom",  scheduleMapper.getScheduleDTO(scheduleFrom));
        model.addAttribute("scheduleTo", scheduleMapper.getScheduleDTO(scheduleTo));
    }

    public void showTicketsByUser(User user, Model model) {
        Passenger passenger = passengerRepo.findByUser(user);
        var mapper = new TicketMapper();
        HashSet<TicketDetailedDTO> ticketDTOs = new HashSet<>();
        for(Ticket ticket : ticketRepo.findAllByPassenger(passenger))
        {
            ticketDTOs.add(mapper.getTiketDtoFromEntity(ticket));
        }
        model.addAttribute("tickets", ticketDTOs);
    }
}
