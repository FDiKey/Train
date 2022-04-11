package com.example.trains.Repo;

import com.example.trains.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepo extends JpaRepository<Route, Long> {
    Route findByNumber(String number);
}
