package com.example.trains.DTO.RouteDTO;

public class RouteFromDTO {

    private Long id;
    private String number;

    public RouteFromDTO(Long id, String routeNumber) {
        this.id = id;
        this.number = routeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setRouteNumber(String number) {
        this.number = number;
    }
}
