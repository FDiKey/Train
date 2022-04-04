package com.example.trains.Servicies;

import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.ScheduleRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.Repo.TrainRepo;
import com.example.trains.domain.Route;
import com.example.trains.domain.Station;
import com.example.trains.domain.Train;
import com.example.trains.domain.Schedule;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ScheduleService {
    @Autowired
    final private StationRepo stationRepo;

    @Autowired
    final private TrainRepo trainRepo;
    @Autowired
    final private ScheduleRepo scheduleRepo;

    public ScheduleService(StationRepo stationRepo, RouteRepo routeRepo, TrainRepo trainRepo, ScheduleRepo scheduleRepo) {
        this.stationRepo = stationRepo;
        this.trainRepo = trainRepo;
        this.scheduleRepo = scheduleRepo;
    }

    //Get first station of route for calculate time
    private Station getFirstStation(Route route) {
        System.out.println(route.getNumber() + " " + route.getId());
        System.out.println(stationRepo.findStationByRouteAndNextStationIsNull(route));
        Station station = stationRepo.findStationByRouteAndNextStationIsNull(route);
        return station;
    }

    //
    public void generateSchedule(Train train){
        Set<Station> stations = getAllStations(train.getRoute()); //get all station of route
        for (Station station: stations) {   //iterate stations to generate schedule
            int count = getCountOfStation(station); // get count
            Schedule schedule = new Schedule();
            schedule.setTrain(train);
            schedule.setStation(station);
            schedule.setOnStation(calculateSchedule(train.getStart(),count));
            scheduleRepo.save(schedule);
        }
    }

    private Set<Station> getAllStations(Route route) {
        return stationRepo.findStationsByRoute(route);
    }

    private Set<Train> getAllTrains(Route route)    {
        Set<Train> trains = trainRepo.findTrainsByRoute(route);
        return trains;
    }
    private int getCountOfStation(Station stationTo){
        Station firstStation = getFirstStation(stationTo.getRoute());
        return getCount(firstStation, stationTo, 0);
    }
    private int getCount(@NotNull Station firstStation, @NotNull Station stationTo, int count) {
        Station nextStation = firstStation.getNextStation();
        if(nextStation != stationTo && nextStation != null){
            count++;
            return getCount(nextStation, stationTo, count);
        }
        count++;
        return count;
    }
    private LocalDateTime calculateSchedule(LocalDateTime startTime, int countOfStations){
        startTime.plusMinutes(countOfStations*5);
        return LocalDateTime.now();
    }

    public void refreshAllSchedule() {
        Iterable<Train> trains = trainRepo.findAll();
        scheduleRepo.deleteAll();
        for(Train train: trains){
            generateSchedule(train);
        }
    }
}
