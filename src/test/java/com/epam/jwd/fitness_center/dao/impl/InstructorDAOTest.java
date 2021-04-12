package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorDAOTest {

    private final static String LOGIN_S = "swarts";
    private final static String NAME_S = "Arnold Schwarzenegger";

    Instructor instructor;

    InstructorDAOImpl instructorDAO;

    static Connection connection;

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    InstructorDAOTest() {
        instructor = EntityManager.ENTITY_MANAGER.createInstructor(0, "login", "name", "pass"
                ,  "dsafa", "asfdasd");
        try {
            connection = DriverManager.getConnection(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        instructorDAO = new InstructorDAOImpl(connection);
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
        assertNotNull(instructorDAO.create(instructor).get());
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

    @Test
    public void FindInstructorByLoginTest_MustReturnCorrectInstructorFromDataBase_Instructor() {
        assertEquals(7, instructorDAO.findByLogin(LOGIN_S).get().getId());
    }

    @Test
    public void FindInstructorByNameTest_MustReturnCorrectInstructorFromDataBase_Instructor() {
        assertEquals(7, instructorDAO.findByName(NAME_S).get().getId());
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
