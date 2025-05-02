package com.group1.roomreservation.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String reservationReference;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime reservationTime;
    private double totalAmount;
    private LocalDateTime confirmationTime;

    @ManyToMany
    private List<Room> rooms;
}