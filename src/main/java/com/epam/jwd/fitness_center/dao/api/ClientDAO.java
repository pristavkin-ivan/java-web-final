package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Client;

import java.util.List;
import java.util.Optional;

/**
 * DAO that is responsible for operating with client's database.
 *
 * @param <T> defines client this DAO works with.
 */
public interface ClientDAO<T extends Client> {

    /**
     * Method that retrieves all saved clients from database.
     *
     * @return List of clients.
     */
    List<T> findAll();

    /**
     * Method that searches for client with appropriate login in database.
     *
     * @param string
     * @return Optional of client
     */
    Optional<T> findByLogin(String string);

    /**
     * Method that searches for client with appropriate id in database.
     *
     * @param id
     * @return Optional of client
     */
    Optional<T> findEntityById(Integer id);

    /**
     * Method that deletes client with appropriate id from database.
     *
     * @param id
     * @return boolean
     */
    boolean delete(Integer id);

    /**
     * Method that deletes client from database.
     *
     * @param entity
     * @return
     */
    boolean delete(T entity);

    /**
     * Method that creates client and adds it to database.
     *
     * @param entity
     * @return Optional of client
     */
    Optional<T> create(T entity);

    /**
     * Method that updates client in database.
     *
     * @param entity
     */
    void update(T entity);

    /**
     * Method that deducts the fee from client's balance.
     *
     * @param id
     * @param newBalance
     */
    void pay(Integer id, Integer newBalance);

    /**
     * Method that increases counter of trainings, which client bought.
     *
     * @param id
     * @param amountOfTrainings
     */
    void increaseAmountOfTrainings(Integer id, Integer amountOfTrainings);
}