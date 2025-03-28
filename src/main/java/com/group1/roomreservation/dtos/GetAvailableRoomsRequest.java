package com.group1.roomreservation.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetAvailableRoomsRequest", namespace = "http://example.com/hotelbooking")
@XmlType(namespace = "http://example.com/hotelbooking")
public class GetAvailableRoomsRequest {
}