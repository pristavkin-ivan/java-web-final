package com.epam.jwd.fitness_center.dao.impl;

import java.sql.Connection;

public enum DAOManager {
    INSTANCE;

    public ExerciseDAO createExerciseDAO(Connection connection) {
        return new ExerciseDAO(connection);
    }

    public EquipmentDAO createEquipmentDAO(Connection connection) {
        return new EquipmentDAO(connection);
    }

    public FoodDAO createFoodDAO(Connection connection) {
        return new FoodDAO(connection);
    }

    public ClientDAOImpl createClientDAO(Connection connection) {
        return new ClientDAOImpl(connection);
    }

    public InstructorDAOImpl createInstructorDAO(Connection connection) {
        return new InstructorDAOImpl(connection);
    }

    public PurposesDAOImpl createPurposesDAO(Connection connection) {
        return new PurposesDAOImpl(connection);
    }

    public TrainingDAOImpl createTrainingDAO(Connection connection) {
        return new TrainingDAOImpl(connection);
    }

}
