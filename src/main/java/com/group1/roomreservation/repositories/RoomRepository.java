package com.group1.roomreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.roomreservation.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByIsAvailableTrue();
}
