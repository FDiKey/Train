package com.example.trains.Servicies;

import com.example.trains.Repo.PassengerRepo;
import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.Repo.TrainRepo;
import com.example.trains.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    final private StationRepo stationRepo;
    @Autowired
    final private PassengerRepo passagerRepo;
    @Autowired
    final private TrainRepo trainRepo;
    @Autowired
    final private RouteRepo routeRepo;
    @Autowired
    final private TicketService ticketService;
    @Autowired
    final private ScheduleService scheduleService;

    public AdminService(StationRepo stationRepo, PassengerRepo passagerRepo, TrainRepo trainRepo, RouteRepo routeRepo, TicketService ticketService, ScheduleService scheduleService) {
        this.stationRepo = stationRepo;
        this.passagerRepo = passagerRepo;
        this.trainRepo = trainRepo;
        this.routeRepo = routeRepo;
        this.ticketService = ticketService;
        this.scheduleService = scheduleService;
    }

    public void getAllStations(Model model){
        Iterable<Station> stations = stationRepo.findAll();
        model.addAttribute("stations", stations);
    }

    public boolean addStaion(String stationName, String route, String previous, String next) {
        Station station = stationRepo.findByName(stationName);
        if(station != null)
            return false;

        /* to do
            create filter via previous and next station
            because only first or last station can have not previous or next station
         */
        Station p = stationRepo.findByName(previous);
        Station n = stationRepo.findByName(next);
        Route r = routeRepo.findByNumber(route);

        Station newStation = new Station(stationName, r != null ? r : null, p != null ? p : null, n != null ? n : null);


        stationRepo.save(newStation);
        return true;
    }

    public Iterable<Route> getAllRoutes() {
        Iterable<Route> routes = routeRepo.findAll();
        return routes;
    }

    public void getRoutes(Model model) {
        Iterable<Route> routes = routeRepo.findAll();
        model.addAttribute("routes", routes);
    }

    public boolean addRoute(String number){
        Route route = routeRepo.findByNumber(number);
        if(route != null){
            return false;
        }
        route = new Route(number);
        routeRepo.save(route);
        return true;
    }

    public boolean addTrain(String trainNumber, String route, String seatCount, String dateStart) {
        if(trainRepo.findByTrainNumber(trainNumber) != null)
            return false;
        Route r = routeRepo.findByNumber(route);

        Train train = new Train(trainNumber, Integer.valueOf(seatCount) , r, LocalDateTime.parse(dateStart));

        trainRepo.save(train);
        scheduleService.generateSchedule(train);
        ticketService.createTickets(trainRepo.findByTrainNumber(train.getTrainNumber()), seatCount);
        return true;
    }

    public void getAllTrains(Model model) {
        Iterable<Train> trains = trainRepo.findAll();
        model.addAttribute("trains", trains);
    }

    public Set<Ticket> getAllTickets(String trainNumber) {
        Train train = trainRepo.findByTrainNumber(trainNumber);
        return train.getTickets();
    }
}
