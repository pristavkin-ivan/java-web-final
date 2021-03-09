package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientDaoTest {

    private final static String TEST_LOGIN = "test_log";

    private final static String TEST_NAME = "Name Name";

    private final static String TEST_PASS = "tes14Pass";

    Client client;

    ClientDAO clientDAO;

    static Connection connection;

    ClientDaoTest() {
        client = new Client(0, TEST_LOGIN, TEST_NAME, TEST_PASS);
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        clientDAO = new ClientDAO(connection);
    }

    @Test
    public void CreateClientTest_MustAddClientToDataBase_True() {
        assertTrue(clientDAO.create(client));
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

    @AfterAll
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}