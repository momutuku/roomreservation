package com.group1.roomreservation.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateClientRequest", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
public class CreateClientRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}