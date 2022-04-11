package com.example.trains.Repo;

import com.example.trains.domain.Passenger;
import com.example.trains.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
        Passenger findByName(String name);

    Passenger findByUser(User user);
}
