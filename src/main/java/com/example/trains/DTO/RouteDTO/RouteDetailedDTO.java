package com.example.trains.DTO.RouteDTO;

import java.util.ArrayList;
import java.util.List;

public class RouteDetailedDTO extends RouteDTO {

    private ArrayList<String> stations = new ArrayList<>();

    public RouteDetailedDTO(Long id, String routeNumber) {
        super(id, routeNumber);
    }

    public List<String> getStations() {
        return stations;
    }

}
