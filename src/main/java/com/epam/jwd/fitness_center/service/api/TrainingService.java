package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.dao.api.ClientDAO;
import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NoSuchTrainingsException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface TrainingService {

    List<TrainingDTO> findAll();

    List<TrainingDTO> findTrainingsByClientId(Integer clientId);

    List<TrainingDTO> findTrainingsByInstructorId(Integer instructorId);

    Optional<TrainingDTO> findTrainingById(Integer id);

    void createTraining(Integer clientId, String instructorName, Integer amount, Integer difficulty
            , Double price, boolean isCredit) throws NoSuchInstructorException, NotEnoughMoneyException;

    void deleteTraining(Integer id);

    void leaveComment(Integer id, String comment);

}
