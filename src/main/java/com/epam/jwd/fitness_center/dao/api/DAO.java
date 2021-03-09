package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface DAO <T extends Entity> {
    List<T> findAll();
    Optional<T> findByString(String string);
    Optional<T> findEntityById(Integer id);
    boolean delete(Integer id);
    abstract boolean delete(T entity);
    abstract boolean create(T entity);
    abstract T update(T entity);
}
