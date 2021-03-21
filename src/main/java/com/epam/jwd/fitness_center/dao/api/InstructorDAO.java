package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface InstructorDAO<T extends Entity> {
    List<T> findAll();
    Optional<T> findByLogin(String string);
    Optional<T> findByName(String string);
    Optional<T> findEntityById(Integer id);
    boolean delete(Integer id);
    abstract boolean delete(T entity);
    abstract Optional<T> create(T entity);
    abstract void update(T entity);
}