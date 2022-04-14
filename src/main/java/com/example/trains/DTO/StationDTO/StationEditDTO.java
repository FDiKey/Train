package com.example.trains.DTO.StationDTO;

public class StationEditDTO extends StationFormDTO {

    private StationFormDTO previous;
    private StationFormDTO next;

    public StationEditDTO(Long id, String name, String routeNumber, StationFormDTO previous, StationFormDTO next)
    {
        super(id, name, routeNumber);
        this.previous = previous;
        this.next = next;
    }

    public StationEditDTO(Long id, String name, String routeNumber) {
        super(id, name, routeNumber);
    }
}
