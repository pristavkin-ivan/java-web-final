package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;

import java.util.Optional;

public interface DAO<T extends Entity> {
    Optional<T> getEntityByName(String name);
}

