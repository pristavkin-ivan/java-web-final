package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.listener.ApplicationListener;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipmentDAOTest {

    EquipmentDAO equipmentDAO;

    static Connection connection;

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    EquipmentDAOTest() {
        try {
            connection = DriverManager.getConnection(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        equipmentDAO = new EquipmentDAO(connection);
    }

    @Test
    public void FindEntityByNameTest_MustSelectExerciseFromDataBase_Exercise() {
        final Optional<Equipment> equipment = equipmentDAO.getEntityByName("Bars");
        assertTrue(equipment.isPresent());
        System.out.println(equipment.get());
    }

    @Test
    public void FindAllTest_MustSelectAllEquipmentFromDataBase_ListOfEquipment() {
        assertTrue(equipmentDAO.findAll().size() > 0);
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
