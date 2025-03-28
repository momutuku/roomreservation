package com.group1.roomreservation.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.group1.roomreservation.dtos.CreateReservationRequest;
import com.group1.roomreservation.dtos.CreateReservationResponse;
import com.group1.roomreservation.dtos.GetClientReservationsRequest;
import com.group1.roomreservation.dtos.GetClientReservationsResponse;
import com.group1.roomreservation.services.ReservationService;

@Endpoint
public class ReservationEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/hotelbooking";
    private final ReservationService reservationService;

    public ReservationEndpoint(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateReservationRequest")
    @ResponsePayload
    public CreateReservationResponse reserveRooms(@RequestPayload CreateReservationRequest request) {
        System.out.println("Received Client ID: " + request.getClientId());
        System.out.println("Received Room IDs: " + request.getRoomIds());

        if (request.getClientId() == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }
        if (request.getRoomIds() == null || request.getRoomIds().isEmpty()) {
            throw new IllegalArgumentException("At least one Room ID must be provided");
        }

        String confirmationMessage = reservationService.reserveRooms(request.getClientId(), request.getRoomIds());

        CreateReservationResponse response = new CreateReservationResponse();
        response.setConfirmationMessage(confirmationMessage);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClientReservationsRequest")
    @ResponsePayload
    public GetClientReservationsResponse getClientReservations(@RequestPayload GetClientReservationsRequest request) {
        // System.out.println("Received request: " + request);
        System.out.println("Client ID: " + request.getClientId());

        return reservationService.getClientReservations(request.getClientId());
    }

}