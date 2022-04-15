package com.example.trains.mapper;

import com.example.trains.mapper.ScheduleDTO.ScheduleDTO;
import com.example.trains.domain.Schedule;

public class ScheduleMapper {

    public ScheduleDTO getScheduleDTO(Schedule schedule){
        return new ScheduleDTO(schedule.getId(), schedule.getStationName(), schedule.getTrainNumber(),schedule.getDateOnStation(), schedule.getTimeOnStation(), schedule.getTrainSeatCount());
    }
}
