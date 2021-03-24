package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipmentDAO implements DAO<Equipment> {

    private final Connection connection;

    private final static Logger LOGGER = LogManager.getLogger(EquipmentDAO.class);

    private final static String SELECT_BY_NAME = "select * from Equipment where e_name = ?";

    private final static String SELECT_ALL = "select * from Equipment";

    private final static String ID_LABEL = "e_id";
    private final static String NAME_LABEL = "e_name";
    private final static String DIFFICULTY_LABEL = "e_difficulty";

    public EquipmentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Equipment> getEntityByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createEquipment(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getInt(DIFFICULTY_LABEL)));

                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Equipment> findAll() {
        List<Equipment> equipment = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
                while(resultSet.next()) {
                    equipment.add(EntityManager.ENTITY_MANAGER.createEquipment(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getInt(DIFFICULTY_LABEL)));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return equipment.stream().skip(1).collect(Collectors.toList());
    }
}
