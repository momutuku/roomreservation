package com.group1.roomreservation.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long clientId;
    private int numberOfRooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
