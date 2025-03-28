package com.group1.roomreservation.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.group1.roomreservation.dtos.CreateClientRequest;
import com.group1.roomreservation.dtos.CreateClientResponse;
import com.group1.roomreservation.models.Client;
import com.group1.roomreservation.services.ClientService;

@Endpoint
public class ClientEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/hotelbooking";
    private final ClientService clientService;

    public ClientEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateClientRequest")
    @ResponsePayload
    public CreateClientResponse createClient(@RequestPayload CreateClientRequest request) {
        Client client = clientService.createClient(request.getName());

        CreateClientResponse response = new CreateClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        return response;
    }
}