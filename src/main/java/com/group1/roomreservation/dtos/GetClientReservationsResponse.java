package com.group1.roomreservation.dtos;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetClientReservationsResponse", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
public class GetClientReservationsResponse {
    private List<ReservedRoomDTO> rooms;
    private double totalCost;

    @XmlElement(name = "rooms")
    public List<ReservedRoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<ReservedRoomDTO> rooms) {
        this.rooms = rooms;
    }

    @XmlElement(name = "totalCost")
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
