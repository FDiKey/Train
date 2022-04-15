package com.example.trains.DTO.TicketDTO.StationDTO;

public class StationDetailedDTO extends StationDTO {

    private StationDTO previous;
    private StationDTO next;

    public StationDetailedDTO(Long id, String name, String routeNumber, StationDTO previous, StationDTO next)
    {
        super(id, name, routeNumber);
        this.previous = previous;
        this.next = next;
    }

    public StationDetailedDTO(Long id, String name, String routeNumber) {
        super(id, name, routeNumber);
    }
}
