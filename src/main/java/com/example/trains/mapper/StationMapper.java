package com.example.trains.mapper;

import com.example.trains.DTO.StationDTO.StationEditDTO;
import com.example.trains.DTO.StationDTO.StationFormDTO;
import com.example.trains.domain.Station;

public class StationMapper {

    public StationFormDTO getStationListDto(Station station){
        return new StationFormDTO(station.getId(), station.getName(), station.getRouteNumber());
    }

    public StationEditDTO getStationEditDto(Station station){
        return new StationEditDTO(station.getId(),
                station.getName(),
                station.getRouteNumber(),
                station.getPreviousStation() != null ? getStationListDto(station.getPreviousStation()) : null,
                station.getNextStation() != null ? getStationListDto(station.getNextStation()) : null);
    }
}
