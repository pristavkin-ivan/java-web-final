package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Purpose;

import java.util.List;

/**
 * DAO that is responsible for operating with purposes database.
 */
public interface PurposesDAO<T extends Purpose> {

    /**
     * Retrieves all purposes with appropriate training's id
     *
     * @param trainingId
     * @return list of purposes
     */
    List<T> findPurposesByTrainingId(Integer trainingId);

    /**
     * Inserts purpose to database
     *
     * @param trainingId
     * @param exerciseId
     * @param equipmentId
     * @param foodId
     */
    void addPurpose(Integer trainingId, Integer exerciseId, Integer equipmentId, Integer foodId);

    /**
     * Deletes all purposes with appropriate training's id
     *
     * @param trainingId
     * @return true if deleting succeed. otherwise - false
     */
    boolean deleteByTrainingId(Integer trainingId);

    /**
     * Method that deletes purpose with appropriate id from database.
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Updates purpose in database
     *
     * @param purposeId
     * @param exerciseId
     * @param equipmentId
     * @param foodId
     */
    void update(Integer purposeId, Integer exerciseId, Integer equipmentId, Integer foodId);
}
