package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.TrainingDAO;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.model.entity.Training;
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

public final class TrainingDAOImpl implements TrainingDAO<Training> {

    private final Connection connection;

    private static final String SELECT_ALL_TRAININGS_BY_CLIENT_ID = "select * from training" +
            " Join client on training.client_id = client.c_id" +
            " Join instructor on training.instructor_id = instructor.i_id" +
            " where client_id = ?";

    private static final String SELECT_ALL_TRAININGS_BY_INSTRUCTOR_ID = "select * from training" +
            " Join client on training.client_id = client.c_id" +
            " Join instructor on training.instructor_id = instructor.i_id" +
            " where instructor_id = ?";

    private static final String SELECT_TRAINING_BY_ID = "select * from training" +
            " Join client on training.client_id = client.c_id" +
            " Join instructor on training.instructor_id = instructor.i_id" +
            " where t_id = ?";

    private static final String SELECT_ALL = "select * from training" +
            " Join client on training.client_id = client.c_id" +
            " Join instructor on training.instructor_id = instructor.i_id";

    private static final String INSERT_TRAINING = "insert into training(client_id, instructor_id, t_amount" +
            ", t_difficulty, t_price) values(?,?,?,?,?) ";

    private static final String UPDATE_COMMENT = "update training set t_comment = ? where t_id = ? ";

    private static final String UPDATE_TRAINING = "update training set client_id = ?, instructor_id = ?, t_amount = ?, " +
            "t_difficulty = ?, t_price = ? where t_id = ? ";

    private static final String DELETE_TRAINING_BY_ID = "delete from training where t_id =?";

    private static final String INSTRUCTOR_ID_LABEL = "instructor.i_id";
    private static final String INSTRUCTOR_NAME_LABEL = "instructor.i_name";
    private static final String INSTRUCTOR_URL_LABEL = "instructor.i_url";
    private static final String INSTRUCTOR_INFO_LABEL = "instructor.i_info";
    private static final String CLIENT_NAME_LABEL = "client.c_name";
    private static final String CLIENT_ID_LABEL = "client_id";


    private final static Logger LOGGER = LogManager.getLogger(TrainingDAOImpl.class);

    private static final String ID_LABEL = "t_id";
    private static final String AMOUNT_LABEL = "t_amount";
    private static final String DIFFICULTY_LABEL = "t_difficulty";
    private static final String PRICE_LABEL = "t_price";
    private static final String COMMENT_LABEL = "t_comment";

    TrainingDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Training.Builder> findAll() {
        List<Training.Builder> builders = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
                while(resultSet.next()) {
                    builders.add(createTraining(resultSet.getInt(CLIENT_ID_LABEL), resultSet));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return builders;
    }

    @Override
    public List<Training.Builder> findAllTrainingsByClientId(Integer clientId) {
        return getBuilders(clientId, SELECT_ALL_TRAININGS_BY_CLIENT_ID);
    }

    @Override
    public List<Training.Builder> findAllTrainingsByInstructorId(Integer instructorId) {
        return getBuilders(instructorId, SELECT_ALL_TRAININGS_BY_INSTRUCTOR_ID);
    }

    @Override
    public Optional<Training.Builder> findEntityById(Integer id) {
        return Optional.of(getBuilders(id, SELECT_TRAINING_BY_ID).get(0));
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRAINING_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void createTraining(Integer clientId, Integer instructorId, Integer amount, Integer difficulty, Double price) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINING)) {
            configureStatement(clientId, instructorId, amount, difficulty, price, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void update(Training entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRAINING)) {
            configureStatement(entity.getClient().getId(), entity.getInstructor().getId(), entity.getAmount()
                    , entity.getDifficulty(), entity.getPrice(), preparedStatement);
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void updateComment(Integer id, String comment) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, comment);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    private void configureStatement(Integer clientId, Integer instructorId, Integer amount, Integer difficulty
            , Double price, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, clientId);
        preparedStatement.setInt(2, instructorId);
        preparedStatement.setInt(3, amount);
        preparedStatement.setInt(4, difficulty);
        preparedStatement.setDouble(5, price);
    }

    private Training.Builder createTraining(Integer clientId, ResultSet resultSet) throws SQLException {
        final Instructor instructor = Instructor.getBuilder().id(resultSet.getInt(INSTRUCTOR_ID_LABEL))
                .name(resultSet.getString(INSTRUCTOR_NAME_LABEL))
                .url(resultSet.getString(INSTRUCTOR_URL_LABEL))
                .info(resultSet.getString(INSTRUCTOR_INFO_LABEL)).build();

        final Client client = Client.getBuilder().id(clientId).name(resultSet.getString(CLIENT_NAME_LABEL)).build();

        return Training.getBuilder()
                .id(resultSet.getInt(ID_LABEL))
                .amount(resultSet.getInt(AMOUNT_LABEL))
                .difficulty(resultSet.getInt(DIFFICULTY_LABEL))
                .instructor(instructor)
                .client(client)
                .price(resultSet.getDouble(PRICE_LABEL))
                .comment(resultSet.getString(COMMENT_LABEL));
    }

    private List<Training.Builder> getBuilders(Integer instructorId, String selectAllTrainingsByInstructorId) {
        List<Training.Builder> builders = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllTrainingsByInstructorId)) {
            preparedStatement.setInt(1, instructorId);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    builders.add(createTraining(instructorId, resultSet));
                }
            }

        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return builders;
    }

}
