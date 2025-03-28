package com.group1.roomreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.roomreservation.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}