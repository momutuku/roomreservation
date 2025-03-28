package com.group1.roomreservation.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.group1.roomreservation.dtos.GetClientReservationsResponse;
import com.group1.roomreservation.dtos.ReservedRoomDTO;
import com.group1.roomreservation.models.Client;
import com.group1.roomreservation.models.ReservedRoom;
import com.group1.roomreservation.models.Room;
import com.group1.roomreservation.repositories.ClientRepository;
import com.group1.roomreservation.repositories.ReservedRoomRepository;
import com.group1.roomreservation.repositories.RoomRepository;

@Service
public class ReservationService {
    private final ClientRepository clientRepository;
    private final RoomRepository roomRepository;
    private final ReservedRoomRepository reservedRoomRepository;

    public ReservationService(ClientRepository clientRepository, RoomRepository roomRepository,
            ReservedRoomRepository reservedRoomRepository) {
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
        this.reservedRoomRepository = reservedRoomRepository;
    }

    public String reserveRooms(Long clientId, List<Long> roomIds) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            return "Client not found";
        }
        Client client = clientOpt.get();
        double totalAmount = 0;

        for (Long roomId : roomIds) {
            Optional<Room> roomOpt = roomRepository.findById(roomId);
            if (roomOpt.isPresent()) {
                Room room = roomOpt.get();

                if (!room.isAvailable()) {
                    return "Room " + room.getName() + " is already reserved";
                }

                totalAmount += room.getPrice();
                ReservedRoom reservedRoom = new ReservedRoom();
                reservedRoom.setClient(client);
                reservedRoom.setRoom(room);
                reservedRoomRepository.save(reservedRoom);

                room.setAvailable(false);
                roomRepository.save(room);
            }
        }
        return "Reservation confirmed for " + client.getName() + " with total cost: " + totalAmount;
    }

    public GetClientReservationsResponse getClientReservations(Long clientId) {
        List<ReservedRoom> reservations = reservedRoomRepository.findByClientId(clientId);

        List<ReservedRoomDTO> rooms = reservations.stream()
                .map(reservedRoom -> new ReservedRoomDTO(reservedRoom.getRoom()))
                .collect(Collectors.toList());

        double totalCost = rooms.stream().mapToDouble(ReservedRoomDTO::getPrice).sum();

        GetClientReservationsResponse response = new GetClientReservationsResponse();
        response.setRooms(rooms);
        response.setTotalCost(totalCost);

        return response;
    }
}
