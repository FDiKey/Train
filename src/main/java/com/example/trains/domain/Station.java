package com.example.trains.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
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

    public String getRouteNumber(){
        return route != null ? route.getNumber() : "none";
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Nullable
    public Station getPreviousStation() {
        return previousStation;
    }

    public void setPreviousStation(@Nullable Station previousStation) {
        this.previousStation = previousStation;
    }

    @Nullable
    public Station getNextStation() {
        return nextStation;
    }

    public void setNextStation(@Nullable Station nextStation) {
        this.nextStation = nextStation;
    }

    public Set<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }

}
