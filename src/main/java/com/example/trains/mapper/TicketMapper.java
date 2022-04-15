package com.example.trains.mapper;

import com.example.trains.DTO.TicketDTO.TicketFullDTO;
import com.example.trains.DTO.TicketDTO.TicketDetailedDTO;
import com.example.trains.domain.Ticket;

public class TicketMapper {

    public TicketDetailedDTO getTiketDtoFromEntity(Ticket ticket){
        return new TicketDetailedDTO(
                                    ticket.getId(),
                                    ticket.getTrain().getTrainNumber(),
                                    ticket.getSeatNumber(),
                                    ticket.getPrice(),
                                    ticket.getFromName(),
                                    ticket.getToName(),
                                    ticket.getScheduleFrom().getDateOnStation(),
                                    ticket.getScheduleTo().getDateOnStation(),
                                    ticket.getScheduleFrom().getTimeOnStation(),
                                    ticket.getScheduleTo().getTimeOnStation());
    }

    public TicketFullDTO getFullTicketDTO(Ticket ticket){
        return new TicketFullDTO(
                ticket.getId(),
                ticket.getTrain().getTrainNumber(),
                ticket.getSeatNumber(),
                ticket.getPrice(),
                ticket.getFromName(),
                ticket.getToName(),
                ticket.getScheduleFrom().getDateOnStation(),
                ticket.getScheduleTo().getDateOnStation(),
                ticket.getScheduleFrom().getTimeOnStation(),
                ticket.getScheduleTo().getTimeOnStation(),
                ticket.getPassenger().getName(),
                ticket.getPassenger().getSurname());
    }

}
