package com.group1.roomreservation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.roomreservation.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findFirstByClientId(Long clientId);
}