package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Purpose;

import java.util.List;

public interface PurposesDAO<T extends Purpose> {

    List<T> findPurposesByTrainingId(Integer trainingId);

    void addPurpose(Integer trainingId, Integer exerciseId, Integer equipmentId, Integer foodId);

    boolean deleteByTrainingId(Integer trainingId);

    void delete(Integer id);

    void update(Integer purposeId, Integer exerciseId, Integer equipmentId, Integer foodId);
}
