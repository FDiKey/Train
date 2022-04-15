package com.example.trains.mapper;

import com.example.trains.DTO.RouteDTO.RouteDetailedDTO;
import com.example.trains.domain.Route;

public class RouteMapper {
    public RouteDetailedDTO getRouteEditForm(Route route){
        return new RouteDetailedDTO(route.getId(), route.getNumber());
    }



}
