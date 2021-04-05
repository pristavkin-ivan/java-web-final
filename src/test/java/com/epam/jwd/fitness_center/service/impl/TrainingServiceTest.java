package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NoSuchTrainingsException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainingServiceTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String ADMIN = "admin";
    private final TrainingServiceImpl trainingService = TrainingServiceImpl.getInstance();

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void FindAllTrainingsTest_MustRetrieveAllSavedTrainings_NotEmptyList() {
        assertTrue(trainingService.findAll().size() > 0);
    }

    @Test
    public void FindAllTrainingsByClientIdTest_MustRetrieveAllSavedTrainingsWithAppropriateClientId_NotEmptyList() {
        assertTrue(trainingService.findTrainingsByClientId(18).size() > 0);
    }

    @Test
    public void FindAllTrainingsByInstructorIdTest_MustRetrieveAllSavedTrainingsWithAppropriateInstructorId_NotEmptyList() {
        assertTrue(trainingService.findTrainingsByInstructorId(7).size() > 0);
    }

    @Test
    public void CreateTrainingTest_MustSaveNewTraining_NotThrowException() {
        assertDoesNotThrow( () -> trainingService.createTraining(35, "Arnold Schwarzenegger"
                , 10, 1, 10.0, false));
    }

    @Test
    public void CreateTrainingTest_MustThrowNoSuchInstructorException_ThrowsNoSuchInstructorException() {
        assertThrows(NoSuchInstructorException.class, () -> trainingService.createTraining(35
                , "dummyInstr", 10, 1, 10.0, false));
    }

    @Test
    public void CreateTrainingTest_MustThrowNoEnoughMoneyException_ThrowsNoEnoughMoneyException() {
        assertThrows(NotEnoughMoneyException.class, () -> trainingService.createTraining(35
                , "Arnold Schwarzenegger", 10, 1, 10000.0, false));
    }

    @Test
    public void DeleteTrainingTest_MustDeleteTraining_NotException() {
        assertDoesNotThrow( () -> trainingService.deleteTraining(34));
    }

    @Test
    public void LeaveCommentTest_MustAddCommentToTraining_NotThrowException() {
        assertDoesNotThrow( () -> trainingService.leaveComment(7, "Leaved comment..."));
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }

}
