package com.example.trains.Servicies;

import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.domain.Route;
import com.example.trains.domain.Schedule;
import com.example.trains.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Set;

@Service
public class SearchService {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StationRepo stationRepo;
    @Autowired
    private RouteRepo routeRepo;


    public Iterable<Schedule> getSchedulesByStationName(String stationName)
    {
        Station station = stationRepo.findByName(stationName);
        if(station != null)
        {
            return scheduleService.getScheduleByStation(station);
        }
        else
            return null;
    }

    public void getScheduleFromToStation(String from, String to, Model model){
        Station stationFrom = stationRepo.findByName(from);
        Station stationTo = stationRepo.findByName(to);
        if(stationFrom != null && stationTo != null) {
            Iterable<Schedule> scheduleFrom = scheduleService.getScheduleByStation(stationFrom);
            Iterable<Schedule> scheduleTo = scheduleService.getScheduleByStation(stationTo);
            if (scheduleFrom != null && scheduleTo != null) {
                model.addAttribute("schedulesFrom", scheduleFrom);
                model.addAttribute("schedulesTo", scheduleTo);
            } else
                model.addAttribute("message", "Fill from/to stations");

// todo calculate cross station
//        if(stationFrom.getRoute() == stationTo.getRoute())
//        {
//            return true;
//        }
//        else {
//            return false;
//        }
        }else{
        model.addAttribute("message", "Incorrect station name");
    }
    }


    public Iterable<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedules();
    }

    public void getMainPage(Model model) {
        HashMap<String, Iterable<Station>> stationByRoute = new HashMap<>();
        var routes = routeRepo.findAll();
        for(Route route : routes)
        {
            stationByRoute.put(route.getNumber(), stationRepo.findStationsByRoute(route));
        }

        model.addAttribute("stationByRoute", stationByRoute);
    }
}
