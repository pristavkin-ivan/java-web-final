package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.ClientDAO;
import com.epam.jwd.fitness_center.dao.api.PurposesDAO;
import com.epam.jwd.fitness_center.dao.api.TrainingDAO;
import com.epam.jwd.fitness_center.dao.api.InstructorDAO;
import com.epam.jwd.fitness_center.dao.impl.ClientDAOImpl;
import com.epam.jwd.fitness_center.dao.impl.InstructorDAOImpl;
import com.epam.jwd.fitness_center.dao.impl.PurposesDAOImpl;
import com.epam.jwd.fitness_center.dao.impl.TrainingDAOImpl;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.model.entity.Training;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class TrainingServiceImpl implements TrainingService {

    private static final TrainingServiceImpl TRAINING_SERVICE = new TrainingServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);
    private static final String NO_SUCH_INSTRUCTOR = "Such Instructor is not exist!";
    private static final String NOT_ENOUGH_MONEY = "Not enough money!";

    private TrainingServiceImpl() {
    }

    @Override
    public List<TrainingDTO> findAll() {
        List<Training> trainings = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDAO purposesDao = new PurposesDAOImpl(connection);

            final List<Training.Builder> trainingBuilders = trainingDAO.findAll();

            for (Training.Builder builder : trainingBuilders) {
                builder.purposes(purposesDao.findPurposesByTrainingId(builder.getId()));
            }

            trainings = trainingBuilders.stream().map(Training.Builder::build)
                    .collect(Collectors.toList());

        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception);
        }

        assert trainings != null;
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> findTrainingsByClientId(Integer clientId) {
        List<Training> trainings = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDAO purposesDao = new PurposesDAOImpl(connection);

            trainings = getTrainingsByClient(clientId, trainingDAO, purposesDao);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception);
        }

        assert trainings != null;
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> findTrainingsByInstructorId(Integer instructorId) {
        List<Training> trainings = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDAO purposesDao = new PurposesDAOImpl(connection);

            trainings = getTrainingsByInstructor(instructorId, trainingDAO, purposesDao);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception);
        }

        assert trainings != null;
        return trainings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TrainingDTO> findTrainingById(Integer id) {
        Training training;

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            PurposesDAO purposesDao = new PurposesDAOImpl(connection);

            training = buildTraining(id, trainingDAO, purposesDao);
            if (training != null) {
                return Optional.of(convertToDto(training));
            }

        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void createTraining(Integer clientId, String instructorName, Integer amount, Integer difficulty
            , Double price, boolean isCredit) throws NoSuchInstructorException, NotEnoughMoneyException {

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            connection.setAutoCommit(false);

            TrainingDAO<Training> trainingDAO = new TrainingDAOImpl(connection);
            InstructorDAO<Instructor> instructorDAO = new InstructorDAOImpl(connection);
            ClientDAO<Client> clientDAO = new ClientDAOImpl(connection);

            final Optional<Instructor> instructor = instructorDAO.findByName(instructorName);

            price = price * getDiscount(clientId, clientDAO);
            validate(clientId, price, isCredit, clientDAO, instructor);
            trainingDAO.createTraining(clientId, instructor.get().getId(), amount, difficulty, price);
            clientDAO.increaseAmountOfTrainings(clientId, amount);

            connection.commit();

        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void deleteTraining(Integer id) {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> dao = new TrainingDAOImpl(connection);
            PurposesDAO p_dao = new PurposesDAOImpl(connection);

            dao.delete(id);
            p_dao.deleteByTrainingId(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void leaveComment(Integer id, String comment) {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            TrainingDAO<Training> dao = new TrainingDAOImpl(connection);

            dao.updateComment(id, comment);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public Double getDiscount(Integer clientId, ClientDAO<Client> clientDAO) {
        final Integer amountOfTrainings = clientDAO.findEntityById(clientId).get().getAmountOfTrainings();
        return Discount.getDiscount(amountOfTrainings);
    }

    private void validate(Integer clientId, Double price, boolean isCredit, ClientDAO<Client> clientDAO
            , Optional<Instructor> instructor) throws NoSuchInstructorException, NotEnoughMoneyException {
        if (!instructor.isPresent()) {
            throw new NoSuchInstructorException(NO_SUCH_INSTRUCTOR);
        }
        final Double balance = clientDAO.findEntityById(clientId).get().getBalance();
        if (balance < price && !isCredit) {
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY);
        }
        clientDAO.pay(clientId, (int) (balance - price));
    }

    private Training buildTraining(Integer id, TrainingDAO<Training> trainingDAO, PurposesDAO purposesDao) {
        Training training = null;
        Training.Builder trainingBuilder;
        Optional<Training.Builder> trainingBuilderOptional;

        trainingBuilderOptional = trainingDAO.findEntityById(id);

        if (trainingBuilderOptional.isPresent()) {
            trainingBuilder = trainingBuilderOptional.get();
            training = trainingBuilder.purposes(purposesDao.findPurposesByTrainingId(trainingBuilder.getId())).build();
        }

        return training;
    }

    private List<Training> getTrainingsByClient(Integer clientId, TrainingDAO<Training> trainingDAO
            , PurposesDAO purposesDao) {

        final List<Training.Builder> trainingBuilders = trainingDAO.findAllTrainingsByClientId(clientId);

        for (Training.Builder builder : trainingBuilders) {
            builder.purposes(purposesDao.findPurposesByTrainingId(builder.getId()));
        }

        return trainingBuilders.stream().map(Training.Builder::build)
                .collect(Collectors.toList());
    }

    private List<Training> getTrainingsByInstructor(Integer instructorId, TrainingDAO<Training> trainingDAO
            , PurposesDAO purposesDao) {

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
                , training.getAmount(), training.getDifficulty(), training.getPurposes(), training.getPrice()
                , training.getComment());
    }

    public static TrainingServiceImpl getInstance() {
        return TRAINING_SERVICE;
    }

    private static class Discount {

        public static final Double NO_DISCOUNT = 1.0;

        public static final Double DISCOUNT_10 = 0.9;

        public static final Double DISCOUNT_20 = 0.8;

        public static final Double DISCOUNT_30 = 0.7;

        public static Double getDiscount(Integer amount) {
            if (amount <= 20)
                return NO_DISCOUNT;
            if (amount <= 50)
                return DISCOUNT_10;
            if (amount <= 80)
                return DISCOUNT_20;
            return DISCOUNT_30;
        }
    }

}
