package com.example.trains.mapper;

import com.example.trains.DTO.RouteDTO.RouteEditDTO;
import com.example.trains.domain.Route;

public class RouteMapper {
    public RouteEditDTO getRouteEditForm(Route route){
        return new RouteEditDTO(route.getId(), route.getNumber());
    }



}
