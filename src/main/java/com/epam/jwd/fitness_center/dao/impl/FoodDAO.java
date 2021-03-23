package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Food;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FoodDAO implements DAO<Food> {

    private final Connection connection;

    private final static Logger LOGGER = LogManager.getLogger(FoodDAO.class);

    private final static String SELECT_BY_NAME = "select * from Food where f_name = ?";

    private final static String ID_LABEL = "f_id";
    private final static String NAME_LABEL = "f_name";
    private final static String WEIGHT_LABEL = "f_weight";
    private final static String CALORIES_LABEL = "f_calories";

    public FoodDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Food> getEntityByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createFood(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getInt(WEIGHT_LABEL)
                            , resultSet.getInt(CALORIES_LABEL)));

                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

}
