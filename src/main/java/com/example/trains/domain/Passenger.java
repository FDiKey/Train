package com.example.trains.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String surName;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Passenger() {}

    public Passenger(String name, String surname) {
        this.name = name;
        this.surName = surname;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

}