package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainingDAOTest {
    Training training;

    TrainingDAOImpl trainingDAO;
    ClientDAOImpl clientDAO;
    InstructorDAOImpl instructorDAO;

    static Connection connection;

    TrainingDAOTest() {
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        trainingDAO = new TrainingDAOImpl(connection);
        clientDAO = new ClientDAOImpl(connection);
        instructorDAO = new InstructorDAOImpl(connection);
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

    @Test
    public void UpdateCommentTest_MustAddCommentToTraining_NotThrowSqlException() {
        assertDoesNotThrow( () -> trainingDAO.updateComment(5, "Leaved comment..."));
    }

    @Test
    public void DeleteTrainingByIdTest_MustDeleteTrainingFromDataBase_True() {
        assertTrue(trainingDAO.delete(33));
    }

    @Test
    public void UpdateTrainingTest_MustUpdateTrainingInDataBase_NotThrowSqlException() {
        assertDoesNotThrow( () -> trainingDAO.update(Training.getBuilder()
                .id(28)
                .client(clientDAO.findEntityById(35).get())
                .instructor(instructorDAO.findEntityById(8).get())
                .amount(33)
                .difficulty(3)
                .price(10.0)
                .build()));
    }

    @Test
    public void FindTrainingByIdTest_MustReturnCorrectTrainingFromDataBase_Training() {
        assertEquals(7, trainingDAO.findEntityById(7).get().getId());
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
