package com.group1.roomreservation.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateRoomRequest", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateRoomRequest {
    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "price", required = true)
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
