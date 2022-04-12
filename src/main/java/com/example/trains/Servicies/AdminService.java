package com.example.trains.Servicies;

import com.example.trains.Repo.*;
import com.example.trains.domain.Route;
import com.example.trains.domain.Station;
import com.example.trains.domain.Ticket;
import com.example.trains.domain.Train;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private StationRepo stationRepo;
    @Autowired
    private PassengerRepo passagerRepo;
    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private RouteRepo routeRepo;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TicketRepo ticketRepo;

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
        Iterable<Route> routes = routeRepo.findAll(Sort.by(Sort.Direction.ASC, "Id"));
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

    public boolean addTrain(String trainNumber, String route, String seatCount, String dateStart, String timeStart) {
        if(trainRepo.findByTrainNumber(trainNumber) != null)
            return false;
        Route r = routeRepo.findByNumber(route);

        Train train = new Train(trainNumber, Integer.valueOf(seatCount) , r, LocalDate.parse(dateStart), LocalTime.parse(timeStart));

        trainRepo.save(train);
        scheduleService.generateSchedule(train);
        return true;
    }

    public void getAllTrains(Model model) {
        Iterable<Train> trains = trainRepo.findAllByIdIsNotNullOrderById();
        model.addAttribute("trains", trains);
    }

    public Set<Ticket> getAllTickets(String trainNumber) {
        Train train = trainRepo.findByTrainNumber(trainNumber);
        Set<Ticket> tickets = ticketRepo.findAllByTrain(train);
        return tickets;
    }

    public boolean updateRoute(@NotNull Route route, String routeNumber, Model model) {
        Route fromDB = routeRepo.findByNumber(route.getNumber());
        if(fromDB.getNumber().equals(routeNumber))
        {
            model.addAttribute("message", "No changes");
            return false;
        }
        else
        {
            fromDB.setNumber(routeNumber);
            routeRepo.save(fromDB);
            model.addAttribute("message", "Changes applied");
            return true;
        }
    }

    public boolean updateStation(@NotNull Station station, String stationName, Model model) {
        Station fromDB = stationRepo.findByName(station.getName());
        if(fromDB.getName().equals(stationName))
        {
            model.addAttribute("message", "No changes");
            return false;
        }
        else
        {
            fromDB.setName(stationName);
            stationRepo.save(fromDB);
            model.addAttribute("message", "Changes applied");
            return true;
        }
    }

    public boolean checkUpdate(Train train,
                               String trainNumber,
                               String routeNumber,
                               String seatCount,
                               LocalDateTime dateStart) {
        Train fromDB = trainRepo.findByTrainNumber(train.getTrainNumber());
        if (
                !fromDB.getTrainNumber().equals(trainNumber) ||
                !fromDB.getRoute().getNumber().equals(routeNumber) ||
                !(fromDB.getSeatCount() == Integer.valueOf(seatCount)) ||
                !fromDB.getDateStart().equals(dateStart.toLocalDate()) ||
                !fromDB.getTimeStart().equals(dateStart.toLocalTime())
        )
        {
            updateTrain(fromDB, trainNumber, routeNumber, Integer.valueOf(seatCount),dateStart);
            return true;
        }
        return false;
    }

    private void updateTrain(Train fromDB, String trainNumber, String routeNumber, int seatCount, LocalDateTime dateStart) {
        Route route = routeRepo.findByNumber(routeNumber);
        fromDB.setTrainNumber(trainNumber);
        fromDB.setRoute(route);
        fromDB.setSeatCount(seatCount);
        fromDB.setDateStart(dateStart.toLocalDate());
        fromDB.setTimeStart(dateStart.toLocalTime());
        trainRepo.save(fromDB);
    }


    public void deleteRoute(Route route) {
        routeRepo.delete(route);
    }


    // implement logic with deletion of tickets and schedule
    public void deleteTrain(Train train) {
        trainRepo.delete(train);
    }
    // implement logic of deletion of staion. (refresh schedule etc)
    public void deleteStation(Station station) {
        stationRepo.delete(station);
    }
}
