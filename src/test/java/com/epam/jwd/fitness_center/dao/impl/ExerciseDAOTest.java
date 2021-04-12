package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseDAOTest {

    ExerciseDAO exerciseDAO;

    static Connection connection;

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    ExerciseDAOTest() {
        try {
            connection = DriverManager.getConnection(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
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

    @Test
    public void FindAllTest_MustSelectAllExercisesFromDataBase_ListOfExercises() {
        assertTrue(exerciseDAO.findAll().size() > 0);
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
