package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface ClientDAO<T extends Entity> {
    List<T> findAll();
    Optional<T> findByLogin(String string);
    Optional<T> findEntityById(Integer id);
    boolean delete(Integer id);
    boolean delete(T entity);
    Optional<T> create(T entity);
    void update(T entity);
    void pay(Integer id, Integer newBalance);
    void increaseAmountOfTrainings(Integer id, Integer amountOfTrainings);
}