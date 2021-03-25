package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service that is responsible for training's business logic.
 */
public interface TrainingService<T extends TrainingDTO> {

    /**
     * Method that retrieves all saved training.
     *
     * @return List of training's DTO
     */
    List<T> findAll();

    /**
     * Method that retrieves all saved training with appropriate client's id.
     *
     * @return List of training's DTO
     */
    List<T> findTrainingsByClientId(Integer clientId);

    /**
     * Method that retrieves all saved training with appropriate instructor's id.
     *
     * @return List of training's DTO
     */
    List<T> findTrainingsByInstructorId(Integer instructorId);

    /**
     * Method that searches for training with appropriate id.
     *
     * @param id
     * @return Optional of trainingDTO
     */
    Optional<T> findTrainingById(Integer id);

    /**
     * Creates training
     *
     * @param clientId
     * @param instructorName
     * @param amount
     * @param difficulty
     * @param price
     * @param isCredit
     * @throws NoSuchInstructorException
     * @throws NotEnoughMoneyException
     */
    void createTraining(Integer clientId, String instructorName, Integer amount, Integer difficulty
            , Double price, boolean isCredit) throws NoSuchInstructorException, NotEnoughMoneyException;

    /**
     * Deletes training with appropriate id
     *
     * @param id
     */
    void deleteTraining(Integer id);

    /**
     * Posts a comment
     *
     * @param id
     * @param comment
     */
    void leaveComment(Integer id, String comment);

}
