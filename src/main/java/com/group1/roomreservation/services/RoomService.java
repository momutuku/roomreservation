package com.group1.roomreservation.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group1.roomreservation.models.Room;
import com.group1.roomreservation.repositories.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public Room createRoom(String name, double price) {
        Room room = new Room();
        room.setName(name);
        room.setPrice(price);
        room.setAvailable(true);
        return repository.save(room);
    }

    public Optional<Room> getRoomDetails(Long roomId) {
        return repository.findById(roomId);
    }

    public List<Room> getAvailableRooms() {
        return repository.findByAvailableTrue();
    }
}