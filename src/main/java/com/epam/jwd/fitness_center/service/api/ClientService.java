package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.entity.Client;

import java.util.List;
import java.util.Optional;

/**
 * Service that is responsible for client's business logic.
 */
public interface ClientService<T extends ClientDTO> {

    /**
     * Method that retrieves all saved clients.
     *
     * @return List of client's DTO
     */
    List<T> findAllClients();

    /**
     * Method that searches for client with appropriate id.
     *
     * @param id
     * @return Optional of clientDTO
     */
    Optional<T> findClientById(Integer id);

    /**
     * Method that authorizes the client.
     *
     * @param login
     * @param password
     * @return Not empty optional if log in operation was successful
     */
    Optional<T> login(String login, String password);

    /**
     * Method that registers client.
     *
     * @param login
     * @param password
     * @param name
     * @return Not empty optional if sign up operation was successful
     */
    Optional<T> signup(String login, String name, String password) throws SignupException;

    /**
     * Method that updates client's profile.
     *
     * @param client
     */
    void updateProfile(Client client);

    /**
     * Method that deletes client's profile
     *
     * @param id
     */
    void deleteProfile(Integer id);

}
