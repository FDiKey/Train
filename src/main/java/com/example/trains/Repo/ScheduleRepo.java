package com.example.trains.Repo;

import com.example.trains.domain.Schedule;
import com.example.trains.domain.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    Set<Schedule> findAllByTrain(Train train);
}
