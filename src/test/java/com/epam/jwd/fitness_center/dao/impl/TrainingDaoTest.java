package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Purposes;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainingDaoTest {
    Training training;

    TrainingDAOImpl trainingDAO;

    static Connection connection;

    TrainingDaoTest() {
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        trainingDAO = new TrainingDAOImpl(connection);
    }

    @Test
    public void FindTrainingByClientIdTest_MustReturnCorrectTrainingBuilderFromDataBase_TrainingBuilder() {
        final List<Training> trainings = trainingDAO.findAllTrainingsByClientId(18).stream()
                .map(Training.Builder::build).collect(Collectors.toList());
        assertNotNull(trainings);
        System.out.println(trainings);
    }

    @Test
    public void CreateTrainingTest_MustAddTrainingToDataBase_NotThrowSqlException() {
        assertDoesNotThrow( () -> trainingDAO.createTraining(training.getClient().getId()
                , training.getInstructor().getId(), training.getAmount(), training.getDifficulty()
                , training.getPrice()));
    }

    @AfterAll
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
