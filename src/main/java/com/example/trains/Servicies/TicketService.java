package com.example.trains.Servicies;

import com.example.trains.Repo.PassengerRepo;
import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.Repo.TicketRepo;
import com.example.trains.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.Set;

@Service
public class TicketService {

    @Autowired
    final private TicketRepo ticketRepo;

    @Autowired
    final private PassengerRepo passengerRepo;

    @Autowired
    final private StationRepo stationRepo;

    public TicketService(TicketRepo ticketRepo, RouteRepo routeRepo, PassengerRepo passengerRepo, StationRepo stationRepo) {
        this.ticketRepo = ticketRepo;
        this.passengerRepo = passengerRepo;
        this.stationRepo = stationRepo;
    }

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
        Set<Station> route = stationRepo.findStationsByRoute(from.getRoute());
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
//                    seats.remove(ticket.getSeatNumber() - 1);
                }
            }
        }


        model.addAttribute("seatCount", seats);  // todo calculate amount of tickets for this station
        model.addAttribute("price", 100 * between.stream().count()); // todo calculate price
        model.addAttribute("scheduleFrom", scheduleFrom);
        model.addAttribute("scheduleTo", scheduleTo);
    }

    public void showTicketsByUser(User user, Model model) {
        Passenger passenger = passengerRepo.findByUser(user);
        Set<Ticket> tickets = ticketRepo.findAllByPassenger(passenger);
        model.addAttribute("tickets", tickets);
    }
}
