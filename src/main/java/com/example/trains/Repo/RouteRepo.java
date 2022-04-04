package com.example.trains.Repo;

import com.example.trains.domain.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepo extends CrudRepository<Route, Long> {
    Route findByNumber(String number);
}
