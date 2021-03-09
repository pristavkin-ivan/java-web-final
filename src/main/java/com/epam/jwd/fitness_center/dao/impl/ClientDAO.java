package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.model.entity.Client;
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

public class ClientDAO implements DAO<Client> {

    private final Connection connection;

    private static final String SELECT_ALL_CLIENTS = "select * from Client";
    private static final String SELECT_CLIENT_BY_ID = "select * from Client where c_id =?";
    private static final String SELECT_CLIENT_BY_LOGIN = "select * from Client where c_login =?";
    private static final String INSERT_CLIENT = "insert into Client(c_login, c_name, c_password) values(?,?,?)";
    private static final String DELETE_CLIENT = "delete from Client where c_login =?";
    private static final String DELETE_CLIENT_BY_ID = "delete from Client where c_id =?";

    private final static Logger LOGGER = LogManager.getLogger(ClientDAO.class);

    private static final String ID_LABEL = "c_id";
    private static final String LOGIN_LABEL = "c_login";
    private static final String NAME_LABEL = "c_name";
    private static final String PASSWORD_LABEL = "c_password";

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CLIENTS)) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt(1), resultSet.getString(3),
                        resultSet.getString(2), resultSet.getString(PASSWORD_LABEL)));
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> findByString(String string) {
        try(PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_LOGIN)) {
            statement.setString(1, string);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Client(resultSet.getInt(ID_LABEL), resultSet.getString(LOGIN_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getString(PASSWORD_LABEL)));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findEntityById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(new Client(resultSet.getInt(ID_LABEL), resultSet.getString(LOGIN_LABEL)
                            , resultSet.getString(NAME_LABEL), resultSet.getString(PASSWORD_LABEL)));
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
    public boolean create(Client entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT)) {
            configureStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Client update(Client entity) {
        /**
         *  добавить по функции update'а для каждого столбца таблицы.
         *  вызывать нужный update на заполненное поле в критерии(билдере)(не null).
         *  */
        return null;
    }

    private void configureStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPassword());
    }
}
