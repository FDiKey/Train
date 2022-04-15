package com.example.trains.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Data
public class Schedule {
    public Schedule() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    @Getter
    @Setter
    private Station station;

    @ManyToOne
    @Getter
    @Setter
    private Train train;

    private LocalDate dateOnStation;

    private LocalTime timeOnStation;



    public String getTrainNumber() {
        return train != null ? train.getTrainNumber() : "none";
    }

    public int getTrainSeatCount()
    {
        return  train.getSeatCount();
    }

    public String getStationName()
    {
        return station.getName();
    }

    public String getOnStation()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateOnStation != null ? dateOnStation.format(formatter) : "00:00:00";
    }

    public LocalDate getDateOnStation(){

        return dateOnStation;
    }

    public LocalTime getTimeOnStation(){
        return timeOnStation;
    }

}
