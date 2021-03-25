package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;

public interface TrainingService<T extends TrainingDTO> {

    List<T> findAll();

    List<T> findTrainingsByClientId(Integer clientId);

    List<T> findTrainingsByInstructorId(Integer instructorId);

    Optional<T> findTrainingById(Integer id);

    void createTraining(Integer clientId, String instructorName, Integer amount, Integer difficulty
            , Double price, boolean isCredit) throws NoSuchInstructorException, NotEnoughMoneyException;

    void deleteTraining(Integer id);

    void leaveComment(Integer id, String comment);

}
