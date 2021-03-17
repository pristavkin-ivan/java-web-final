package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Purposes;

import java.util.List;

public interface PurposesDao {

    Purposes findPurposesByTrainingId(Integer trainingId);

    void addPurposes(List<Purposes> purposes, Integer trainingId);
}
