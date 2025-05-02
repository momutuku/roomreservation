package com.group1.roomreservation.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.roomreservation.models.Client;
import com.group1.roomreservation.models.Reservation;
import com.group1.roomreservation.models.Room;
import com.group1.roomreservation.repositories.ClientRepository;
import com.group1.roomreservation.repositories.ReservationRepository;
import com.group1.roomreservation.repositories.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private RoomRepository roomRepo;

    public Reservation makeReservation(Long clientId, int numberOfRooms, LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms = roomRepo.findByIsAvailableTrue();

        if (availableRooms.size() < numberOfRooms) {
            throw new IllegalStateException("Not enough rooms available");
        }

        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = availableRooms.get(0).getPrice() * numberOfRooms * days;
        LocalDateTime now = LocalDateTime.now();

        Reservation res = new Reservation();
        res.setClient(client);
        res.setCheckInDate(checkIn);
        res.setCheckOutDate(checkOut);
        res.setReservationReference(UUID.randomUUID().toString());
        res.setReservationTime(now);
        res.setConfirmationTime(now);
        res.setTotalAmount(total);
        res.setRooms(availableRooms);

        for (Room room : availableRooms) {
            room.setAvailable(false);
        }
        roomRepo.saveAll(availableRooms);

        return reservationRepo.save(res);
    }

    public Optional<Reservation> getByClientId(Long clientId) {
        return reservationRepo.findFirstByClientId(clientId);
    }
}
