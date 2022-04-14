package com.example.trains.mapper;

import com.example.trains.DTO.TrainDTO.TrainDTO;
import com.example.trains.domain.Train;

public class TrainMapper {

    public TrainDTO getDTOfromEntity(Train train)
    {
        return new TrainDTO(train.getId(),
                train.getTrainNumber(),
                train.getRoute().getNumber(),
                train.getDateStart(),
                train.getTimeStart(),
                train.getTickets());
    }
}
