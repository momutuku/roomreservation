package com.group1.roomreservation.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.group1.roomreservation.dtos.CreateRoomRequest;
import com.group1.roomreservation.dtos.CreateRoomResponse;
import com.group1.roomreservation.dtos.GetAvailableRoomsRequest;
import com.group1.roomreservation.dtos.GetAvailableRoomsResponse;
import com.group1.roomreservation.dtos.GetRoomDetailsRequest;
import com.group1.roomreservation.dtos.GetRoomDetailsResponse;
import com.group1.roomreservation.models.Room;
import com.group1.roomreservation.services.RoomService;

@Endpoint
public class RoomEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/hotelbooking";
    private final RoomService roomService;

    public RoomEndpoint(RoomService roomService) {
        this.roomService = roomService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateRoomRequest")
    @ResponsePayload
    public CreateRoomResponse createRoom(@RequestPayload CreateRoomRequest request) {
        Room room = roomService.createRoom(request.getName(), request.getPrice());

        CreateRoomResponse response = new CreateRoomResponse();
        response.setName(room.getName());
        response.setPrice(room.getPrice());
        response.setAvailability(room.isAvailable());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetRoomDetailsRequest")
    @ResponsePayload
    public GetRoomDetailsResponse getRoomDetails(@RequestPayload GetRoomDetailsRequest request) {
        Optional<Room> roomOpt = roomService.getRoomDetails(request.getRoomId());
        GetRoomDetailsResponse response = new GetRoomDetailsResponse();
        roomOpt.ifPresent(room -> {
            response.setName(room.getName());
            response.setPrice(room.getPrice());
            response.setAvailable(room.isAvailable());
        });
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAvailableRoomsRequest")
    @ResponsePayload
    public GetAvailableRoomsResponse getAvailableRooms(@RequestPayload GetAvailableRoomsRequest request) {
        List<Room> availableRooms = roomService.getAvailableRooms();
        GetAvailableRoomsResponse response = new GetAvailableRoomsResponse();
        response.setRooms(availableRooms);
        return response;
    }
}
