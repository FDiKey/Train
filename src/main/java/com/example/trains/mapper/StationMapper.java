package com.example.trains.mapper;

import com.example.trains.DTO.TicketDTO.StationDTO.StationDetailedDTO;
import com.example.trains.DTO.TicketDTO.StationDTO.StationDTO;
import com.example.trains.domain.Station;

public class StationMapper {

    public StationDTO getStationListDto(Station station){
        return new StationDTO(station.getId(), station.getName(), station.getRouteNumber());
    }

    public StationDetailedDTO getStationEditDto(Station station){
        return new StationDetailedDTO(station.getId(),
                station.getName(),
                station.getRouteNumber(),
                station.getPreviousStation() != null ? getStationListDto(station.getPreviousStation()) : null,
                station.getNextStation() != null ? getStationListDto(station.getNextStation()) : null);
    }
}
