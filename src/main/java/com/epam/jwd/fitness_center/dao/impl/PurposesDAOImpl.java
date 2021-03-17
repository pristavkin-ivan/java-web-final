package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.PurposesDao;
import com.epam.jwd.fitness_center.model.entity.Entity;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import com.epam.jwd.fitness_center.model.entity.Food;
import com.epam.jwd.fitness_center.model.entity.Purposes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PurposesDAOImpl implements PurposesDao {

    private final Connection connection;

    private final static EntityManager ENTITY_MANAGER = EntityManager.ENTITY_MANAGER;

    private static final String SELECT_PURPOSE_BY_TRAINING = "select exercise.e_name, exercise.e_difficulty" +
            ", exercise.e_repetitions, equipment.e_name, equipment.e_difficulty, food.f_name, food.f_weight" +
            ", food.f_calories " +
            "from Purposes " +
            "Join exercise on purposes.exercise_id = exercise.e_id " +
            "Join equipment on purposes.equipment_id = equipment.e_id " +
            "Join food on purposes.food_id = food.f_id " +
            "where training_id =?";

    private static final String INSERT_PURPOSE = "insert into purposes(training_id, exercise_id, equipment_id, food_id)" +
            " values(?,?,?,?)";

    private final static Logger LOGGER = LogManager.getLogger(ClientDAO.class);
    private final String EXERCISE_NAME_LABEL = "exercise.e_name";
    private final String EXERCISE_DIFFICULTY_LABEL = "exercise.e_difficulty";
    private final String EXERCISE_REPETITIONS_LABEL = "exercise.e_repetitions";
    private final String EQUIPMENT_NAME_LABEL = "equipment.e_name";
    private final String EQUIPMENT_DIFFICULTY_LABEL = "equipment.e_difficulty";
    private final String FOOD_NAME_LABEL = "food.f_name";
    private final String FOOD_WEIGHT_LABEL = "food.f_weight";
    private final String FOOD_CALORIES_LABEL = "food.f_calories";

    public PurposesDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Purposes findPurposesByTrainingId(Integer trainingId) {
        List<Exercise> exercises = new ArrayList<>();
        List<Equipment> equipment = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_PURPOSE_BY_TRAINING)) {
            statement.setInt(1, trainingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    fillCollections(exercises, equipment, foods, resultSet);
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        }
        return createPurpose(trainingId, exercises, equipment, foods);
    }

    @Override
    public void addPurposes(List<Purposes> purposes, Integer trainingId) {

    }

    private void fillCollections(List<Exercise> exercises, List<Equipment> equipment, List<Food> foods, ResultSet resultSet) throws SQLException {
        exercises.add(ENTITY_MANAGER.createExercise(resultSet.getString(EXERCISE_NAME_LABEL)
                , resultSet.getInt(EXERCISE_DIFFICULTY_LABEL)
                , resultSet.getInt(EXERCISE_REPETITIONS_LABEL)));
        equipment.add(ENTITY_MANAGER.createEquipment(resultSet.getString(EQUIPMENT_NAME_LABEL)
                , resultSet.getInt(EQUIPMENT_DIFFICULTY_LABEL)));
        foods.add(ENTITY_MANAGER.createFood(resultSet.getString(FOOD_NAME_LABEL)
                , resultSet.getInt(FOOD_WEIGHT_LABEL), resultSet.getInt(FOOD_CALORIES_LABEL)));
    }

    private Purposes createPurpose(Integer trainingId, List<Exercise> exercises, List<Equipment> equipment, List<Food> foods) {
        exercises = exercises.stream().filter((val)-> !val.getName().equalsIgnoreCase(""))
                .collect(Collectors.toList());
        equipment = equipment.stream().filter((val)-> !val.getName().equalsIgnoreCase(""))
                .collect(Collectors.toList());
        foods = foods.stream().filter((val)-> !val.getName().equalsIgnoreCase(""))
                .collect(Collectors.toList());
        return ENTITY_MANAGER.createPurposes(trainingId, exercises, equipment, foods);
    }
}
