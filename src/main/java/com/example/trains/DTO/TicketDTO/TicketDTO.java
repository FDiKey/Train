package com.example.trains.DTO.TicketDTO;

import com.example.trains.domain.Schedule;
import com.example.trains.domain.Station;
import com.example.trains.domain.Train;

import java.util.Set;

public class TicketDTO {
    private Long id;
    private Train train;
    private int seatNumber;
    private double price;
    private Station from;
    private Station to;
    private Schedule scheduleFrom;
    private Schedule scheduleTo;
    private Set<Station> stationBetween;
}
