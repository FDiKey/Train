package com.example.trains.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String trainNumber;
    private int seatCount;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
    private LocalDateTime start;

    public Train(String trainNumber, int seatCount, Route route, LocalDateTime start) {
        this.trainNumber = trainNumber;
        this.seatCount = seatCount;
        this.route = route;
        this.start = start;
    }

    public Train() {

    }
}
