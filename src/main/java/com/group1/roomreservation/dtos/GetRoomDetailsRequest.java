package com.group1.roomreservation.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetRoomDetailsRequest", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
public class GetRoomDetailsRequest {
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
