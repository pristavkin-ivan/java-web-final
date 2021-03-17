package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Purposes;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PurposesDaoTest {

    PurposesDAOImpl purposesDao;

    static Connection connection;

    PurposesDaoTest() {
        try {
            connection = DriverManager.getConnection(ApplicationListener.URL, ApplicationListener.USER
                    , ApplicationListener.PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        purposesDao = new PurposesDAOImpl(connection);
    }

    @Test
    public void FindPurposesByTrainingIdTest_MustReturnCorrectPurposesFromDataBase_Purposes() {
        final Purposes purposes = purposesDao.findPurposesByTrainingId(1);
        assertNotNull(purposes);
        System.out.println(purposes);
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
