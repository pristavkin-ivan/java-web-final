package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Training;

import java.util.List;
import java.util.Optional;

/**
 * DAO that is responsible for operating with client's database.
 *
 * @param <T> defines client this DAO works with.
 */
public interface TrainingDAO<T extends Training> {

    /**
     * Method that retrieves all saved trainings without purposes from database.
     *
     * @return List of training builders.
     */
    List<T.Builder> findAll();

    /**
     * Method that retrieves all saved trainings without purposes with appropriate client's id from database.
     *
     * @return List of training builders.
     */
    List<T.Builder> findAllTrainingsByClientId(Integer clientId);

    /**
     * Method that retrieves all saved trainings without purposes with appropriate instructor's id from database.
     *
     * @return List of training builders.
     */
    List<T.Builder> findAllTrainingsByInstructorId(Integer instructorId);

    /**
     * Method that retrieves training builder with appropriate id from database.
     *
     * @return Optional of training builder.
     */
    Optional<T.Builder> findEntityById(Integer id);

    /**
     * Method that deletes training with appropriate id from database.
     *
     * @param id
     * @return boolean
     */
    boolean delete(Integer id);

    /**
     * Inserts training to database
     *
     * @param clientId
     * @param instructorId
     * @param amount
     * @param difficulty
     * @param price
     */
    void createTraining(Integer clientId, Integer instructorId, Integer amount, Integer difficulty, Double price);

    /**
     * Method that updates training in database.
     *
     * @param entity
     */
    void update(T entity);

    /**
     * Method that updates comment in database.
     *
     * @param id
     * @param comment
     */
    void updateComment(Integer id, String comment);

}
