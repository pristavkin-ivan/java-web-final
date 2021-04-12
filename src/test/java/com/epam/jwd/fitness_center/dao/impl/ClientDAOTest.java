package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
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

public class ClientDAOTest {

    private final static String TEST_LOGIN = "test_log";
    private final static String TEST_NAME = "Name Name";
    private final static String TEST_PASS = "tes14Pass";
    private final static String LOGIN_V = "vane4k";

    Client client;

    ClientDAOImpl clientDAO;

    static Connection connection;

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    ClientDAOTest() {
        client = EntityManager.ENTITY_MANAGER.createClient(0,4, TEST_LOGIN, TEST_NAME, TEST_PASS, 100.0
                , 100.0, 540.0);
        try {
            connection = DriverManager.getConnection(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        clientDAO = new ClientDAOImpl(connection);
    }

    @Test
    public void UpdateClientTest_MustUpdateClientInDataBase_NotThrowSqlException() {
        assertDoesNotThrow( () -> clientDAO.update(Client.getBuilder().id(0)
                .login("asdasd")
                .name("asd")
                .height(150.0)
                .weight(150.0)
                .build()));
    }

    @Test
    public void IncreaseAmountOfTrainingsTest_MustIncreaseTrainings_NotThrowSqlException() {
        assertDoesNotThrow( () -> clientDAO.increaseAmountOfTrainings(35, 10));
    }

    @Test
    public void CreateClientTest_MustAddClientToDataBase_True() {
        assertNotNull(clientDAO.create(client).get());
    }

    @Test
    public void PayTest_MustUpdateClientBalance_NotThrowSqlException() {
        clientDAO.pay(18, 500);
    }

    @Test
    public void DeleteClientTest_MustDeleteClientFromDataBase_True() {
        assertTrue(clientDAO.delete(client));
    }

    @Test
    public void DeleteClientByIdTest_MustDeleteClientFromDataBase_True() {
        assertTrue(clientDAO.delete(12));
    }

    @Test
    public void FindAllClientsTest_MustReturnAllClientsFromDataBase_NotEmptyList() {
        assertNotNull(clientDAO.findAll());
    }

    @Test
    public void FindClientByIdTest_MustReturnCorrectClientFromDataBase_Client() {
        assertEquals(3, clientDAO.findEntityById(3).get().getId());
    }

    @Test
    public void FindClientByLoginTest_MustReturnCorrectClientFromDataBase_Client() {
        assertEquals(18, clientDAO.findByLogin(LOGIN_V).get().getId());
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
