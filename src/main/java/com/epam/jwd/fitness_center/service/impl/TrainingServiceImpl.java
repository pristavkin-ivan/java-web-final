package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.PurposesDao;
import com.epam.jwd.fitness_center.dao.api.TrainingDAO;
import com.epam.jwd.fitness_center.dao.impl.PurposesDAOImpl;
import com.epam.jwd.fitness_center.dao.impl.TrainingDAOImpl;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.model.entity.Training;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public final class TrainingServiceImpl implements TrainingService {

    private static final TrainingServiceImpl TRAINING_SERVICE = new TrainingServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private TrainingServiceImpl() {
    }

    @Override
    public List<TrainingDTO> getTrainingsByClientId(Integer clientId) {
        List<Training> trainings = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDao purposesDao = new PurposesDAOImpl(connection);

            trainings = getTrainingsByClient(clientId, trainingDAO, purposesDao);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception);
        }

        assert trainings != null;
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> getTrainingsByInstructorId(Integer instructorId) {
        List<Training> trainings = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDao purposesDao = new PurposesDAOImpl(connection);

            trainings = getTrainingsByInstructor(instructorId, trainingDAO, purposesDao);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception);
        }

        assert trainings != null;
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private List<Training> getTrainingsByClient(Integer clientId, TrainingDAO<Training> trainingDAO, PurposesDao purposesDao) {
        final List<Training.Builder> trainingBuilders = trainingDAO.findAllTrainingsByClientId(clientId);

        for (Training.Builder builder : trainingBuilders) {
            builder.purposes(purposesDao.findPurposesByTrainingId(builder.getId()));
        }

        return trainingBuilders.stream().map(Training.Builder::build)
                .collect(Collectors.toList());
    }

    private List<Training> getTrainingsByInstructor(Integer instructorId, TrainingDAO<Training> trainingDAO, PurposesDao purposesDao) {
        final List<Training.Builder> trainingBuilders = trainingDAO.findAllTrainingsByInstructorId(instructorId);

        for (Training.Builder builder : trainingBuilders) {
            builder.purposes(purposesDao.findPurposesByTrainingId(builder.getId()));
        }

        return trainingBuilders.stream().map(Training.Builder::build)
                .collect(Collectors.toList());
    }

    private TrainingDTO convertToDto(Training training) {
        return DTOManager.DTO_MANAGER.createTrainingDTO(training.getId(), training.getInstructor().getName()
                , training.getClient().getName(), training.getInstructor().getImgUrl()
                , training.getAmount(), training.getDifficulty(), training.getPurposes());
    }

    public static TrainingServiceImpl getInstance() {
        return TRAINING_SERVICE;
    }

}
