package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.PurposesDAO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import com.epam.jwd.fitness_center.model.entity.Food;
import com.epam.jwd.fitness_center.model.entity.Purpose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class PurposesDAOImpl implements PurposesDAO<Purpose> {

    private final Connection connection;

    private final static EntityManager ENTITY_MANAGER = EntityManager.ENTITY_MANAGER;

    private static final String SELECT_PURPOSE_BY_TRAINING = "select * from Purposes " +
            "Join exercise on purposes.exercise_id = exercise.e_id " +
            "Join equipment on purposes.equipment_id = equipment.e_id " +
            "Join food on purposes.food_id = food.f_id " +
            "where training_id =?";

    private static final String INSERT_PURPOSE = "insert into purposes(training_id, exercise_id, equipment_id, food_id)" +
            " values(?,?,?,?)";

    private static final String UPDATE_PURPOSE = "update purposes set exercise_id = ?, equipment_id = ?, " +
            "food_id = ? where p_id = ? ";

    private static final String DELETE_PURPOSES_BY_TRAINING_ID = "delete from purposes where training_id =?";

    private static final String DELETE_PURPOSES_BY_ID = "delete from purposes where p_id =?";

    private final static Logger LOGGER = LogManager.getLogger(PurposesDAOImpl.class);
    private final static String ID_LABEL = "p_id";
    private final static String EXERCISE_NAME_LABEL = "exercise.e_name";
    private final static String EXERCISE_ID_LABEL = "exercise.e_id";
    private final static String EXERCISE_DIFFICULTY_LABEL = "exercise.e_difficulty";
    private final static String EXERCISE_REPETITIONS_LABEL = "exercise.e_repetitions";
    private final static String EQUIPMENT_NAME_LABEL = "equipment.e_name";
    private final static String EQUIPMENT_DIFFICULTY_LABEL = "equipment.e_difficulty";
    private final static String EQUIPMENT_ID_LABEL= "equipment.e_id";
    private final static String FOOD_NAME_LABEL = "food.f_name";
    private final static String FOOD_WEIGHT_LABEL = "food.f_weight";
    private final static String FOOD_CALORIES_LABEL = "food.f_calories";
    private final static String FOOD_ID_LABEL= "food.f_id";

    PurposesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Purpose> findPurposesByTrainingId(Integer trainingId) {
        final List<Purpose> purposes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_PURPOSE_BY_TRAINING)) {
            statement.setInt(1, trainingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    purposes.add(createPurpose(trainingId, resultSet));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        }
        return purposes;
    }

    @Override
    public void addPurpose(Integer trainingId, Integer exerciseId, Integer equipmentId, Integer foodId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PURPOSE)) {
            configureInsertStatement(trainingId, exerciseId, equipmentId, foodId, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public boolean deleteByTrainingId(Integer trainingId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PURPOSES_BY_TRAINING_ID)) {
            preparedStatement.setInt(1, trainingId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PURPOSES_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void update(Integer purposeId, Integer exerciseId, Integer equipmentId, Integer foodId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PURPOSE)) {
            configureUpdateStatement(purposeId, exerciseId, equipmentId, foodId, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    private void configureInsertStatement(Integer trainingId, Integer exerciseId, Integer equipmentId
            , Integer foodId, PreparedStatement statement) throws SQLException {

        statement.setInt(1, trainingId);
        statement.setInt(2, exerciseId);
        statement.setInt(3, equipmentId);
        statement.setInt(4, foodId);
    }

    private void configureUpdateStatement(Integer purposeId, Integer exerciseId, Integer equipmentId
            , Integer foodId, PreparedStatement statement) throws SQLException {

        statement.setInt(1, exerciseId);
        statement.setInt(2, equipmentId);
        statement.setInt(3, foodId);
        statement.setInt(4, purposeId);
    }

    private Purpose createPurpose(Integer trainingId, ResultSet resultSet) throws SQLException {
        final Exercise exercise = ENTITY_MANAGER.createExercise(resultSet.getInt(EXERCISE_ID_LABEL)
                , resultSet.getString(EXERCISE_NAME_LABEL), resultSet.getInt(EXERCISE_DIFFICULTY_LABEL)
                , resultSet.getInt(EXERCISE_REPETITIONS_LABEL));

        final Equipment equipment = ENTITY_MANAGER.createEquipment(resultSet.getInt(EQUIPMENT_ID_LABEL)
                , resultSet.getString(EQUIPMENT_NAME_LABEL)
                , resultSet.getInt(EQUIPMENT_DIFFICULTY_LABEL));

        final Food food = ENTITY_MANAGER.createFood(resultSet.getInt(FOOD_ID_LABEL)
                , resultSet.getString(FOOD_NAME_LABEL)
                , resultSet.getInt(FOOD_WEIGHT_LABEL), resultSet.getInt(FOOD_CALORIES_LABEL));

        return ENTITY_MANAGER.createPurposes(resultSet.getInt(ID_LABEL), trainingId, exercise, equipment, food);
    }

}
