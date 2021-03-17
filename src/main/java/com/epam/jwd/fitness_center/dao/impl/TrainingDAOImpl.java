package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.TrainingDAO;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.model.entity.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static final String SELECT_TRAINING_BY_ID = "select * from training where i_id =?";
    private static final String INSERT_TRAINING = "insert into training(client_id, instructor_id, t_amount" +
            ", t_difficulty) values(?,?,?,?) ";

    //todo продумать по какой
    private static final String DELETE_TRAINING = "delete from training where client_login =?";
    private static final String DELETE_TRAINING_BY_ID = "delete from training where t_id =?";

    private static final String INSTRUCTOR_ID_LABEL = "instructor.i_id";
    private static final String INSTRUCTOR_NAME_LABEL = "instructor.i_name";
    private static final String INSTRUCTOR_URL_LABEL = "instructor.i_url";
    private static final String INSTRUCTOR_INFO_LABEL = "instructor.i_info";
    private static final String CLIENT_NAME_LABEL = "client.c_name";


    private final static Logger LOGGER = LogManager.getLogger(ClientDAO.class);

    private static final String ID_LABEL = "t_id";
    private final String AMOUNT_LABEL = "t_amount";
    private final String DIFFICULTY_LABEL = "t_difficulty";

    public TrainingDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Training.Builder> findAllTrainingsByClientId(Integer clientId) {
        return getBuilders(clientId, SELECT_ALL_TRAININGS_BY_CLIENT_ID);
    }

    @Override
    public List<Training.Builder> findAllTrainingsByInstructorId(Integer instructorId) {
        return getBuilders(instructorId, SELECT_ALL_TRAININGS_BY_INSTRUCTOR_ID);
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
                .client(client);
    }

    @Override
    public Optional<Training.Builder> findByString(String string) {
        return Optional.empty();
    }

    @Override
    public Optional<Training.Builder> findEntityById(Integer id) {
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
    public void create(Training entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINING)) {
            configureStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void update(Training entity) {
    }

    private void configureStatement(Training entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getClient().getId());
        preparedStatement.setInt(2, entity.getInstructor().getId());
        preparedStatement.setInt(3, entity.getAmount());
        preparedStatement.setInt(3, entity.getDifficulty());
    }

}
