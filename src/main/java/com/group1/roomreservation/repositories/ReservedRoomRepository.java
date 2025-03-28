package com.group1.roomreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.roomreservation.models.ReservedRoom;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {
    List<ReservedRoom> findByClientId(Long clientId);
}