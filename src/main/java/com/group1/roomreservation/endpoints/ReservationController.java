package com.group1.roomreservation.endpoints;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
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
            Map<String, Object> response = new HashMap<>();
            response.put("client_name", res.getClient().getName());
            response.put("hotel_name", res.getRooms().get(0).getHotelName());
            response.put("amount", res.getTotalAmount());
            response.put("check_in", res.getCheckInDate());
            response.put("check_out", res.getCheckOutDate());
            response.put("days", ChronoUnit.DAYS.between(res.getCheckInDate(), res.getCheckOutDate()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    e.getMessage()));
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getByClient(@PathVariable Long clientId) {
        try {
            return service.getByClientId(clientId)
                    .map(res -> {
                        Map<String, Object> response = new HashMap<>();
                        response.put("client_name", res.getClient().getName());
                        response.put("hotel_name", res.getRooms().get(0).getHotelName());
                        response.put("rooms_given", res.getRooms().size());
                        response.put("total_amount", res.getTotalAmount());
                        response.put("reservation_date", res.getReservationTime());
                        response.put("check_in", res.getCheckInDate());
                        response.put("check_out", res.getCheckOutDate());
                        response.put("confirmation_number", res.getReservationReference());
                        response.put("contact_info", "0712345678 | reservations@bluelagoon.ke");
                        return ResponseEntity.ok(response);
                    })
                    .orElseThrow();
        } catch (Exception e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}