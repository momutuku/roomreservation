package com.group1.roomreservation.dtos;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateReservationRequest", namespace = "http://example.com/hotelbooking")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "clientId", "roomIds" })
public class CreateReservationRequest {

    private Long clientId;

    @XmlElementWrapper(name = "roomIds") // Wraps the list under <roomIds>
    @XmlElement(name = "roomId") // Each item in the list is <roomId>
    private List<Long> roomIds;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Long> roomIds) {
        this.roomIds = roomIds;
    }
}