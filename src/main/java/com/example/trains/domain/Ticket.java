package com.example.trains.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private Train train;

    private int seatNumber;
    private double price;
    @OneToOne
    private Station from;
    @OneToOne
    private Station to;

    @OneToOne
    private Schedule scheduleFrom;

    @OneToOne
    private Schedule scheduleTo;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Station> stationBetween;

    @ManyToOne
    private Passenger passenger;

    public Ticket(Passenger passenger, Train train, Schedule from, Schedule to,  Set<Station> stationBetween,  int seatNumber) {
        this.passenger = passenger;
        this.train = train;
        this.from = from.getStation();
        this.to = to.getStation();
        this.scheduleFrom = from;
        this.scheduleTo = to;
        this.seatNumber = seatNumber;
        this.price = price;
        this.stationBetween = stationBetween;
    }

//    public Schedule getScheduleFrom() {
//        return scheduleFrom;
//    }

    public void setScheduleFrom(Schedule scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    public Schedule getScheduleTo() {
        return scheduleTo;
    }

    public void setScheduleTo(Schedule scheduleTo) {
        this.scheduleTo = scheduleTo;
    }

    public String getFromName()
    {
        return from.getName();
    }

    public String getToName(){
        return to.getName();
    }

}
