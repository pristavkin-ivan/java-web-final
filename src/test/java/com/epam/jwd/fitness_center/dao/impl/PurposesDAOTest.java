package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Purpose;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PurposesDAOTest {

    PurposesDAOImpl purposesDao;

    static Connection connection;

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    PurposesDAOTest() {
        try {
            connection = DriverManager.getConnection(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        purposesDao = new PurposesDAOImpl(connection);
    }

    @Test
    public void FindPurposesByTrainingIdTest_MustReturnCorrectPurposesFromDataBase_Purposes() {
        final List<Purpose> purposes = purposesDao.findPurposesByTrainingId(1);
        assertTrue(purposes.size() > 0);
        System.out.println(purposes);
    }

    @Test
    public void DeletePurposesByTrainingId_MustDeleteAllPurposesById_True() {
        assertTrue(purposesDao.deleteByTrainingId(2));
    }

    @Test
    public void UpdatePurpose_MustUpdatePurpose_NotThrowSqlException() {
        assertDoesNotThrow( () -> purposesDao.update(9, 3, 1, 3));
    }

    @Test
    public void AddPurpose_MustInsertPurposeToDataBase_NotThrowSqlException() {
        assertDoesNotThrow( () -> purposesDao.addPurpose(3, 2, 2 , 2));
    }

    @Test
    public void DeletePurpose_MustDeletePurposeFromDataBase_NotThrowSqlException() {
        assertDoesNotThrow( () -> purposesDao.delete(27));
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
