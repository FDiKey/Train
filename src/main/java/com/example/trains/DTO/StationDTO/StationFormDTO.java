package com.example.trains.DTO.StationDTO;

public class StationFormDTO {
    private Long id;
    private String name;
    private String routeNumber;

    public StationFormDTO(Long id, String name, String routeNumber) {
        this.id = id;
        this.name = name;
        this.routeNumber = routeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }
}
