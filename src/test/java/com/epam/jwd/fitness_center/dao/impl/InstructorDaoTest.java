package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorDaoTest {
    private final static String TEST_LOGIN = "test_log";

    private final static String TEST_NAME = "Name Name";

    private final static String TEST_PASS = "tes14Pass";

    Instructor instructor;

    InstructorDAO instructorDAO;

    static Connection connection;

    InstructorDaoTest() {
        instructor = EntityManager.ENTITY_MANAGER.createInstructor(0, "login", "name", "pass"
                , null, "dsafa", "asfdasd");
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        instructorDAO = new InstructorDAO(connection);
    }

    @Test
    public void UpdateClientTest_MustUpdateClientInDataBase_NotThrowSqlException() {
        final Instructor instructor = Instructor.getBuilder().id(0)
                .login("sdfsdf")
                .name("trytr4y")
                .url("reter")
                .info("asdfa")
                .build();

        assertDoesNotThrow( () -> instructorDAO.update(instructor));
    }

    @Test
    public void CreateInstructorTest_MustAddInstructorToDataBase_True() {
        assertTrue(instructorDAO.create(instructor));
    }

    @Test
    public void DeleteInstructorTest_MustDeleteInstructorFromDataBase_True() {
        assertTrue(instructorDAO.delete(instructor));
    }

    @Test
    public void DeleteInstructorByIdTest_MustDeleteInstructorFromDataBase_True() {
        assertTrue(instructorDAO.delete(12));
    }

    @Test
    public void FindAllInstructorTest_MustReturnAllInstructorFromDataBase_NotEmptyList() {
        assertNotNull(instructorDAO.findAll());
    }

    @Test
    public void FindInstructorByIdTest_MustReturnCorrectInstructorFromDataBase_Instructor() {
        assertEquals(3, instructorDAO.findEntityById(3).get().getId());
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
