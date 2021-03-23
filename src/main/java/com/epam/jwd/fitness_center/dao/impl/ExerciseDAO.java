package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class ExerciseDAO implements DAO<Exercise> {

    private final Connection connection;

    private final static Logger LOGGER = LogManager.getLogger(ExerciseDAO.class);

    private final static String SELECT_BY_NAME = "select * from Exercise where e_name = ?";

    private final static String ID_LABEL = "e_id";
    private final static String NAME_LABEL = "e_name";
    private final static String DIFFICULTY_LABEL = "e_difficulty";
    private final static String REPETITIONS_LABEL = "e_repetitions";

    public ExerciseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Exercise> getEntityByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createExercise(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getInt(DIFFICULTY_LABEL)
                            , resultSet.getInt(REPETITIONS_LABEL)));

                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }
}
