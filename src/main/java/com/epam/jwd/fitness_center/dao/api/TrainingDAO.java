package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDAO<T extends Training> {
    List<Training.Builder> findAll();
    List<Training.Builder> findAllTrainingsByClientId(Integer clientId);
    List<Training.Builder> findAllTrainingsByInstructorId(Integer instructorId);
    Optional<Training.Builder> findEntityById(Integer id);
    boolean delete(Integer id);
    void createTraining(Integer clientId, Integer instructorId, Integer amount, Integer difficulty, Double price);
    void update(T entity);
    void updateComment(Integer id, String comment);

}
