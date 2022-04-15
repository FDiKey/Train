package com.example.trains.DTO.TicketDTO;

public class TicketDTO {
    private Long id;
    private String stationFrom;
    private String stationTo;
    private String trainNumber;

    public TicketDTO(Long id, String trainNumber, String stationFrom, String stationTo) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
}
