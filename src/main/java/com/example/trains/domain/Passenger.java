package com.example.trains.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    private String name;
    private String surname;

    private LocalDate bDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    public Passenger() {}

    public Passenger(User user)
    {
        this.user = user;
        this.name = "";
        this.surname = "";
    }

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname == null ? "" : surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getbDay() {
        return bDay;
    }

    public void setbDay(LocalDate bDay) {
        this.bDay = bDay;
    }
}