package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.ClientDAO;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
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

public final class ClientDAOImpl implements ClientDAO<Client> {

    private final Connection connection;

    private static final String SELECT_ALL_CLIENTS = "select * from Client";
    private static final String SELECT_CLIENT_BY_ID = "select * from Client where c_id =?";
    private static final String SELECT_CLIENT_BY_LOGIN = "select * from Client where c_login =?";
    private static final String INSERT_CLIENT = "insert into Client(c_login, c_name, c_password) values(?,?,?)";
    private static final String DELETE_CLIENT = "delete from Client where c_login =?";
    private static final String DELETE_CLIENT_BY_ID = "delete from Client where c_id =?";
    private static final String UPDATE_CLIENT = "update Client set c_login = ?,c_name = ?,c_height = ?" +
            ",c_weight = ? where c_id = ?";
    private static final String UPDATE_BALANCE = "update Client set c_balance= ? where c_id = ?";
    private static final String UPDATE_AMOUNT_OF_TRAININGS = "update Client set c_amount_of_trainings = " +
            "c_amount_of_trainings + ? where c_id = ?";

    private final static Logger LOGGER = LogManager.getLogger(ClientDAOImpl.class);

    private static final String ID_LABEL = "c_id";
    private static final String LOGIN_LABEL = "c_login";
    private static final String NAME_LABEL = "c_name";
    private static final String PASSWORD_LABEL = "c_password";
    private static final String HEIGHT_LABEL = "c_height";
    private static final String WEIGHT_LABEL = "c_weight";
    private static final String BALANCE_LABEL = "c_balance";
    private static final String TRAININGS_AMOUNT_LABEL = "c_amount_of_trainings";

    ClientDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CLIENTS)) {
            while (resultSet.next()) {
                clients.add(EntityManager.ENTITY_MANAGER.createClient(resultSet.getInt(ID_LABEL)
                        , resultSet.getInt(TRAININGS_AMOUNT_LABEL)
                        , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                        , resultSet.getString(PASSWORD_LABEL), resultSet.getDouble(HEIGHT_LABEL)
                        , resultSet.getDouble(WEIGHT_LABEL), resultSet.getDouble(BALANCE_LABEL)));
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> findByLogin(String string) {
        try(PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_LOGIN)) {
            statement.setString(1, string);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createClient(resultSet.getInt(ID_LABEL)
                            , resultSet.getInt(TRAININGS_AMOUNT_LABEL)
                            , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                            , resultSet.getString(PASSWORD_LABEL), resultSet.getDouble(HEIGHT_LABEL)
                            , resultSet.getDouble(WEIGHT_LABEL), resultSet.getDouble(BALANCE_LABEL)));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findEntityById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createClient(resultSet.getInt(ID_LABEL)
                            , resultSet.getInt(TRAININGS_AMOUNT_LABEL)
                            , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                            , resultSet.getString(PASSWORD_LABEL), resultSet.getDouble(HEIGHT_LABEL)
                            , resultSet.getDouble(WEIGHT_LABEL), resultSet.getDouble(BALANCE_LABEL)));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Client entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<Client> create(Client entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT)) {
            configureStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return Optional.empty();
        }
        return findByLogin(entity.getLogin());
    }

    @Override
    public void update(Client entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT)) {
            configureUpdateStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void pay(Integer id, Integer newBalance) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void increaseAmountOfTrainings(Integer id, Integer amountOfTrainings) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AMOUNT_OF_TRAININGS)) {
            preparedStatement.setInt(1, amountOfTrainings);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    private void configureUpdateStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setDouble(3, entity.getHeight());
        preparedStatement.setDouble(4, entity.getWeight());
        preparedStatement.setInt(5, entity.getId());
    }

    private void configureStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPassword());
    }
}
