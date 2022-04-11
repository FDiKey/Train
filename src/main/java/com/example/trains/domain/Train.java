package com.example.trains.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Data
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainNumber;
    private int seatCount;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
    private LocalDate dateStart;
    private LocalTime timeStart;

    public Train(String trainNumber, int seatCount, Route route, LocalDate start, LocalTime time) {
        this.trainNumber = trainNumber;
        this.seatCount = seatCount;
        this.route = route;
        this.dateStart = start;
        this.timeStart = time;
    }

    public Train() {

    }

    public String getRouteNumber(){
        return route != null ? route.getNumber() : "none";
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDate getDateStart(){
        return dateStart;
    }
    public LocalTime getTimeStart()
    {
        return timeStart;
    }


    // todo equals and hashcode
}
