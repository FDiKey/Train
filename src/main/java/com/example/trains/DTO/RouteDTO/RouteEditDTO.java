package com.example.trains.DTO.RouteDTO;

import java.util.ArrayList;
import java.util.List;

public class RouteEditDTO extends RouteFromDTO{

    private ArrayList<String> stations = new ArrayList<>();

    public RouteEditDTO(Long id, String routeNumber) {
        super(id, routeNumber);
    }

    public List<String> getStations() {
        return stations;
    }

}
