package com.example.trains.Repo;

import com.example.trains.domain.Passenger;
import com.example.trains.domain.Ticket;
import com.example.trains.domain.Train;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
    Set<Ticket> findAllByTrain(Train train);
    Set<Ticket> findAllByPassenger(Passenger passenger);
}
