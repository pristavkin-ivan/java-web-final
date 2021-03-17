package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainingServiceTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String ADMIN = "admin";

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void getTrainingsTest_ReturnTrainingsDtoList_NotEmptyList() {
        final List<TrainingDTO> trainingsDto = TrainingServiceImpl.getInstance().getTrainingsByClientId(18);

        assertNotNull(trainingsDto);
        System.out.println(trainingsDto);
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }

}