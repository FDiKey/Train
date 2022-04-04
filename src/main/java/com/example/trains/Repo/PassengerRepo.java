package com.example.trains.Repo;

import com.example.trains.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
        Passenger findByName(String name);
}
