package com.example.trains.Repo;

import com.example.trains.domain.Route;
import com.example.trains.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StationRepo extends JpaRepository<Station, Long> {
    Station findByName(String name);
    Station findStationByRouteAndNextStationIsNull(Route route);
    Station findStationByRouteAndPreviousStationIsNull(Route route);
    Set<Station> findStationsByRoute(Route route);
}
