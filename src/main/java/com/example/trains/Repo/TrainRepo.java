package com.example.trains.Repo;

import com.example.trains.domain.Route;
import com.example.trains.domain.Train;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TrainRepo extends CrudRepository<Train, Long> {
        Train findByTrainNumber(String number);
        Set<Train> findTrainsByRoute(Route route);
}
