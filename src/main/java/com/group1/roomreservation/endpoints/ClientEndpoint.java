package com.group1.roomreservation.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group1.roomreservation.models.Client;
import com.group1.roomreservation.repositories.ClientRepository;
import com.group1.roomreservation.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientEndpoint {
    private final ClientService clientService;
    @Autowired
    private ClientRepository repo;

    public ClientEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return repo.save(client);
    }
}