package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.TrainingDAO;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TrainingDAOImpl implements TrainingDAO<Training> {

    private final Connection connection;

    private static final String SELECT_ALL_TRAININGS = "select * from training";
    private static final String SELECT_TRAINING_BY_ID = "select * from training where i_id =?";

    //todo продумать по какой
    private static final String DELETE_TRAINING = "delete from training where client_login =?";
    private static final String DELETE_TRAINING_BY_ID = "delete from training where t_id =?";

    private final static Logger LOGGER = LogManager.getLogger(ClientDAO.class);

    private static final String ID_LABEL = "t_id";

    public TrainingDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Training> findAll() {
        return null;
    }

    @Override
    public Optional<Training> findByString(String string) {
        return Optional.empty();
    }

    @Override
    public Optional<Training> findEntityById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Training entity) {
        return false;
    }

    @Override
    public boolean create(Training entity) {
        return false;
    }

    @Override
    public Training update(Training entity) {
        return null;
    }
}
