package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurposeServiceTest {
    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String TEST = "test";

    private final PurposeServiceImpl purposeService = PurposeServiceImpl.getInstance();

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void CreatePurposeTest_MustCreatePurpose_NotThrowException() {
        assertDoesNotThrow( () -> purposeService.createPurpose(31, "squats", "", "banana"));
    }

    @Test
    public void UpdatePurposeTest_MustUpdatePurpose_NotThrowException() {
        assertDoesNotThrow( () -> purposeService.updatePurpose(33, "", "", "banana"));
    }

    @Test
    public void DeletePurposeTest_MustDeletePurpose_NotThrowException() {
        assertDoesNotThrow( () -> purposeService.deletePurpose(33));
    }

    @Test
    public void FindAllFoodTest_MustRetrieveAllSavedFood_NotEmptyList() {
        assertTrue(purposeService.getAllFood().size() > 0);
    }

    @Test
    public void FindAllExercisesTest_MustRetrieveAllSavedExercises_NotEmptyList() {
        assertTrue(purposeService.getAllExercises().size() > 0);
    }

    @Test
    public void FindAllEquipmentTest_MustRetrieveAllSavedEquipment_NotEmptyList() {
        assertTrue(purposeService.getAllEquipment().size() > 0);
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }
}
