package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Purpose;

import java.util.List;

public interface PurposesDAO {

    List<Purpose> findPurposesByTrainingId(Integer trainingId);

    void addPurpose(Purpose purpose, Integer trainingId);

    boolean deleteByTrainingId(Integer trainingId);

    void delete(Integer id);
}
