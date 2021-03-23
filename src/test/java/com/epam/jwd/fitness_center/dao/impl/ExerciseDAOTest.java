package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseDAOTest {

    ExerciseDAO exerciseDAO;

    static Connection connection;

    ExerciseDAOTest() {
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        exerciseDAO = new ExerciseDAO(connection);
    }

    @Test
    public void FindEntityByNameTest_MustSelectExerciseFromDataBase_Exercise() {
        final Optional<Exercise> burpee = exerciseDAO.getEntityByName("Burpee");
        assertTrue(burpee.isPresent());
        System.out.println(burpee.get());
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
