package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Entity;
import com.epam.jwd.fitness_center.model.entity.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDAO<T extends Entity> {
    List<Training.Builder> findAllTrainingsByClientId(Integer clientId);
    List<Training.Builder> findAllTrainingsByInstructorId(Integer instructorId);
    Optional<Training.Builder> findByString(String string);
    Optional<Training.Builder> findEntityById(Integer id);
    boolean delete(Integer id);
    abstract boolean delete(T entity);
    abstract void create(T entity);
    abstract void update(T entity);
}
