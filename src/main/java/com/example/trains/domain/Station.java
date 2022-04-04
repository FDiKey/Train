package com.example.trains.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @Getter
    @Setter
    private long Id;
    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @Getter
    @Setter
    private Route route;


    @ManyToOne
    @Getter
    @Setter
    @Nullable
    private Station previousStation;

    @ManyToOne
    @Getter
    @Setter
    @Nullable
    private Station nextStation;

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Schedule> schedule;

    public Station(String name, Route route, Station previous, Station next)
    {
        this.name = name;
        this.route = route;
    }

    public Station() {

    }
}
