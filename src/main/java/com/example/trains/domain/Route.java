package com.example.trains.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @NotNull
    private Long id;

    @Getter
    @Setter
    private String number;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Station> stations;

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    public Route(){}
    public Route(String number)
    {
        this.number = number;
    }

    public Long getId(){
        return id;
    }

    // todo equals and hashcode
}
