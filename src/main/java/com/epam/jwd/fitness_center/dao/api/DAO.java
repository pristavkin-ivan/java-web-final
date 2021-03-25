package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;

import java.util.List;
import java.util.Optional;

/**
 * DAO that is responsible for operating with exercise's, equipment's and food's database.
 *
 * @param <T> defines entity this DAO works with.
 */
public interface DAO<T extends Entity> {

    /**
     * Searches entity by name
     *
     * @param name
     * @return Optional of entity if entity is exist. Otherwise - Optional.empty
     */
    Optional<T> getEntityByName(String name);

    /**
     * Method that retrieves all saved entities from database.
     *
     * @return List of entities.
     */
    List<T> findAll();
}

