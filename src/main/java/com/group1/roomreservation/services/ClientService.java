package com.group1.roomreservation.services;

import org.springframework.stereotype.Service;

import com.group1.roomreservation.models.Client;
import com.group1.roomreservation.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client createClient(String name) {
        Client client = new Client();
        client.setName(name);
        return repository.save(client);
    }
}