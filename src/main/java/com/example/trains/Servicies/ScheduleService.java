package com.example.trains.Servicies;

import com.example.trains.Repo.ScheduleRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.Repo.TrainRepo;
import com.example.trains.domain.Route;
import com.example.trains.domain.Schedule;
import com.example.trains.domain.Station;
import com.example.trains.domain.Train;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Service
public class ScheduleService {
    @Autowired
    private StationRepo stationRepo;

    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;

    //Get first station of route for calculate time
    private Station getFirstStation(Route route) {
        Station station = stationRepo.findStationByRouteAndPreviousStationIsNull(route);
        return station;
    }

    //Genereted schedule for new train
    public void generateSchedule(Train train){
        Set<Station> stations = getAllStations(train.getRoute()); //get all station of route
        for (Station station: stations) {   //iterate stations to generate schedule
            int count = getCountOfStation(station); // get count
            Schedule schedule = new Schedule();
            schedule.setTrain(train);
            schedule.setStation(station);
            // Calculate date and time from station to station
            LocalDateTime temp = calculateSchedule(train.getDateStart(), train.getTimeStart(), count);
            schedule.setDateOnStation(temp.toLocalDate());
            schedule.setTimeOnStation(temp.toLocalTime());
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
        // get start station from route and calculate amount of station to destination.
        Station firstStation = getFirstStation(stationTo.getRoute());
        if(firstStation != stationTo) {
            return getCount(firstStation, stationTo, 0);
        }
        return 0;
    }
    private int getCount(@NotNull Station firstStation, @NotNull Station stationTo, int count) {
        Station nextStation = firstStation.getNextStation();
        if(nextStation != stationTo && nextStation != null){
            count = getCount(nextStation, stationTo, count);
        }
        count++;
        return count;
    }

    private LocalDateTime calculateSchedule(LocalDate startDate, LocalTime startTime, int countOfStations){
        // union date and time to calculate time on destination station
        LocalDateTime dt = LocalDateTime.of(startDate, startTime);
        // constant time between stations 20 minutes
        if(countOfStations > 0)
            return dt.plusMinutes(countOfStations * 20);
        else
            return dt;
    }

    public void refreshAllSchedule() {
        Iterable<Train> trains = trainRepo.findAll();
        scheduleRepo.deleteAll();
        for(Train train: trains){
            generateSchedule(train);
        }
    }

    public Iterable<Schedule> getScheduleByStation(Station station) {
        return scheduleRepo.findSchedulesByStation(station);
    }

    public Iterable<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }
}
