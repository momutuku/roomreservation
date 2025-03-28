package com.group1.roomreservation.dtos;

import com.group1.roomreservation.models.Room;

import lombok.Data;

@Data
public class ReservedRoomDTO {
    private String name;
    private double price;

    public ReservedRoomDTO(Room room) {
        this.name = room.getName();
        this.price = room.getPrice();
    }

}