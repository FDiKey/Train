package com.example.trains.mapper.ScheduleDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDTO {
    private Long id;
    private String stationName;
    private String trainNumber;
    private int seatCount;
    private LocalDate date;
    private LocalTime time;

    public ScheduleDTO(Long id, String stationName, String trainNumber, LocalDate date, LocalTime time, int seatCount) {
        this.id = id;
        this.stationName = stationName;
        this.trainNumber = trainNumber;
        this.date = date;
        this.time = time;
        this.seatCount = seatCount;
    }


    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
