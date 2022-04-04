package com.example.trains.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private Train train;
    private int seatNumber;
    private double price;
    @OneToOne
    private Station from;
    @OneToOne
    private Station to;


    @ManyToOne
    private Passenger passenger;

    public Ticket(){}

    public Ticket(Train train, int seatNumber) {
        this.train = train;
        this.seatNumber = seatNumber;
        this.price = price;
    }
}
