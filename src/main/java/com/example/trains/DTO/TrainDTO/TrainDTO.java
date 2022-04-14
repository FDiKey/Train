package com.example.trains.DTO.TrainDTO;

import com.example.trains.DTO.TicketDTO.TicketDTO;
import com.example.trains.domain.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class TrainDTO {
    private Long id;
    private String trainNumber;
    private String routeNumber;
    private LocalDate dateStart;
    private LocalTime timeStart;
    private Set<TicketDTO> tickets;

    public TrainDTO(Long id, String trainNumber, String routeNumber, LocalDate dateStart, LocalTime timeStart, Set<Ticket> tickets) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.routeNumber = routeNumber;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        HashSet<TicketDTO> ticketDTOs = new HashSet<TicketDTO>();
        for(Ticket ticket : tickets){
            ticketDTOs.add(new TicketDTO());
        }
        this.tickets = ticketDTOs;
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

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
