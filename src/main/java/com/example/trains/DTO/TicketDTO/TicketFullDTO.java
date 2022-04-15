package com.example.trains.DTO.TicketDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketFullDTO extends TicketDetailedDTO {
    private String passengerName;
    private String passengerSurname;

    public TicketFullDTO(Long id, String trainNumber, int seatNumber, double price, String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo) {
        super(id, trainNumber, seatNumber, price, stationFrom, stationTo, dateFrom, dateTo, timeFrom, timeTo);
    }

    public TicketFullDTO(Long id, String trainNumber, int seatNumber, double price, String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo, String passengerName, String passengerSurname) {
        super(id, trainNumber, seatNumber, price, stationFrom, stationTo, dateFrom, dateTo, timeFrom, timeTo);
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }
}

