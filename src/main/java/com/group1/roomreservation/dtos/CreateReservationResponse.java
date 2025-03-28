package com.group1.roomreservation.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateReservationResponse", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
public class CreateReservationResponse {
    private String confirmationMessage;

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }
}