package com.group1.roomreservation.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.roomreservation.models.Room;
import com.group1.roomreservation.repositories.RoomRepository;
import com.group1.roomreservation.services.RoomService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/rooms")
public class RoomEndpoint {
    private final RoomService roomService;
    private final RoomRepository repo;

    public RoomEndpoint(RoomService roomService, RoomRepository repo) {
        this.roomService = roomService;
        this.repo = repo;
    }

    @PostMapping("/")
    public Room createRoom(@RequestBody Room room) {
        room.setAvailable(true);
        return repo.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoomDetails(@PathVariable Long id) {
        Optional<Room> roomOpt = roomService.getRoomDetails(id);
        if (roomOpt.isPresent()) {
            return roomOpt.get();
        } else {
            throw new EntityNotFoundException("Room not found");
        }
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();

    }
}
