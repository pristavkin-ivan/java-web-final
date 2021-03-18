package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDTO> getAllClients();

    Optional<ClientDTO> getClientById(Integer id);

    Optional<ClientDTO> login(String login, String password);

    void signup(String login, String name, String password) throws SignupException;

    void updateProfile(Client client);

}
