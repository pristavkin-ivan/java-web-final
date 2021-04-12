package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.NoSuchPurposeException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurposeServiceTest {

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    private static final String TEST = "test";

    private final PurposeServiceImpl purposeService = PurposeServiceImpl.getInstance();

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void CreatePurposeTest_MustCreatePurpose_NotThrowException() {
        assertDoesNotThrow( () -> purposeService.createPurpose(31, "squats", "", "banana"));
    }

    @Test
    public void CreatePurposeTest_MustThrowNoSuchPurposeException_NotThrowNoSuchPurposeException() {
        assertThrows(NoSuchPurposeException.class
                , () -> purposeService.createPurpose(31, "??", "", "banana"));
    }

    @Test
    public void UpdatePurposeTest_MustUpdatePurpose_NotThrowException() {
        assertDoesNotThrow( () -> purposeService.updatePurpose(33, "", "", "banana"));
    }

    @Test
    public void UpdatePurposeTest_MustThrowNoSuchPurposeException_NotThrowNoSuchPurposeException() {
        assertThrows(NoSuchPurposeException.class
                , () ->  purposeService.updatePurpose(33, "", "??", "banana"));
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
