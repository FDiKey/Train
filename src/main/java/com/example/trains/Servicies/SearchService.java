package com.example.trains.Servicies;

import com.example.trains.mapper.ScheduleDTO.ScheduleDTO;
import com.example.trains.DTO.TicketDTO.StationDTO.StationDTO;
import com.example.trains.Repo.RouteRepo;
import com.example.trains.Repo.StationRepo;
import com.example.trains.domain.Schedule;
import com.example.trains.domain.Station;
import com.example.trains.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;

@Service
public class SearchService {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StationRepo stationRepo;
    @Autowired
    private RouteRepo routeRepo;


    public HashSet<ScheduleDTO> getScheduleDtosByStationName(String stationName)
    {
        Station station = stationRepo.findByName(stationName);
        HashSet<ScheduleDTO> scheduleDtos = new HashSet<ScheduleDTO>();
        if(station != null)
        {
            for(Schedule schedule : scheduleService.getScheduleByStation(station))
                scheduleDtos.add(new ScheduleDTO(schedule.getId(),
                        schedule.getStationName(),
                        schedule.getTrainNumber(),
                        schedule.getDateOnStation(),
                        schedule.getTimeOnStation(),
                        schedule.getTrainSeatCount()));
            return scheduleDtos;
        }
        else
            return null;
    }

    public void setScheduleFromByStation(String from, String to, Model model){
        var stationFrom = stationRepo.findByName(from);
        var stationTo = stationRepo.findByName(to);

        if(stationFrom != null && stationTo != null) {
            var scheduleFrom = getScheduleDtosByStationName(from);
            var scheduleTo = getScheduleDtosByStationName(to);
            if (scheduleFrom != null && scheduleTo != null) {
                model.addAttribute("resFrom", from);
                model.addAttribute("resTo", to);
                model.addAttribute("schedulesFrom", scheduleFrom);
                model.addAttribute("schedulesTo", scheduleTo);
            } else
                model.addAttribute("message", "Fill from/to stations");
// todo calculate cross station
//        if(stationFrom.getRoute() == stationTo.getRoute())
//        {
//            get Schedules from to staitons
//        }
//        else {
//            get cross staiton from stationFrom route and stationTo route
//            get schedules from stationFrom to  cross station
//            get schedule from cross station to stationTo
//        }
        }else{
        model.addAttribute("message", "Incorrect station name");
    }
    }


    public Iterable<Schedule> getAllSchedule() {
        return scheduleService.getAllSchedules();
    }

    public HashSet<StationDTO>  getMainPage(Model model) {
        var stations = stationRepo.findAll();
        var stationMapper = new StationMapper();
        HashSet<StationDTO> stationDTO = new HashSet<>();
        for (Station station : stations){
            stationDTO.add(stationMapper.getStationListDto(station));
        }
        return stationDTO;
    }
}
