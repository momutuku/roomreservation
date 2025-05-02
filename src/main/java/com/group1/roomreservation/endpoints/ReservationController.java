package com.group1.roomreservation.endpoints;

import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.roomreservation.dtos.ReservationRequest;
import com.group1.roomreservation.models.Reservation;
import com.group1.roomreservation.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<?> reserve(@RequestBody ReservationRequest req) {
        try {
            Reservation res = service.makeReservation(req.getClientId(), req.getNumberOfRooms(),
                    req.getCheckInDate(), req.getCheckOutDate());
            return ResponseEntity.ok(Map.of(
                    "client_name", res.getClient().getName(),
                    "hotel_name", res.getRooms().get(0).getHotelName(),
                    "amount", res.getTotalAmount(),
                    "check_in", res.getCheckInDate(),
                    "check_out", res.getCheckOutDate(),
                    "days", ChronoUnit.DAYS.between(res.getCheckInDate(), res.getCheckOutDate())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getByClient(@PathVariable Long clientId) {
        return service.getByClientId(clientId)
                .map(res -> Map.of(
                        "client_name", res.getClient().getName(),
                        "hotel_name", res.getRooms().get(0).getHotelName(),
                        "rooms_given", res.getRooms().size(),
                        "total_amount", res.getTotalAmount(),
                        "reservation_date", res.getReservationTime(),
                        "check_in", res.getCheckInDate(),
                        "check_out", res.getCheckOutDate(),
                        "confirmation_number", res.getReservationReference(),
                        "contact_info", "0712345678 | reservations@bluelagoon.ke"))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No reservation found")));
    }
}