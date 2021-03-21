package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.InstructorDAO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Instructor;
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

public final class InstructorDAOImpl implements InstructorDAO<Instructor> {

    private final Connection connection;

    private static final String SELECT_ALL_INSTRUCTORS = "select * from instructor";
    private static final String SELECT_INSTRUCTOR_BY_ID = "select * from instructor where i_id =?";
    private static final String SELECT_INSTRUCTOR_BY_LOGIN = "select * from instructor where i_login =?";
    private static final String SELECT_INSTRUCTOR_BY_NAME = "select * from instructor where i_name =?";
    private static final String INSERT_INSTRUCTOR = "insert into instructor(i_login, i_name, i_password) values(?,?,?)";
    private static final String DELETE_INSTRUCTOR = "delete from instructor where c_login =?";
    private static final String DELETE_INSTRUCTOR_BY_ID = "delete from instructor where i_id =?";
    private static final String UPDATE_INSTRUCTOR = "update Instructor set i_login = ?,i_name = ?, i_url = ?" +
            ", i_info = ? where i_id = ?";

    private final static Logger LOGGER = LogManager.getLogger(ClientDAOImpl.class);

    private static final String ID_LABEL = "i_id";
    private static final String LOGIN_LABEL = "i_login";
    private static final String NAME_LABEL = "i_name";
    private static final String PASSWORD_LABEL = "i_password";
    private static final String URL_LABEL = "i_url";
    private static final String INFO_LABEL = "i_info";
    private final String ADMIN = "admin";

    public InstructorDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Instructor> findAll() {
        List<Instructor> instructors = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_INSTRUCTORS)) {
            while (resultSet.next()) {
                instructors.add(EntityManager.ENTITY_MANAGER.createInstructor(resultSet.getInt(ID_LABEL)
                        , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                        , resultSet.getString(PASSWORD_LABEL), resultSet.getString(URL_LABEL)
                        , resultSet.getString(INFO_LABEL)));
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return instructors;
    }

    @Override
    public Optional<Instructor> findByLogin(String string) {
        return findByString(string, SELECT_INSTRUCTOR_BY_LOGIN);
    }

    @Override
    public Optional<Instructor> findByName(String string) {
        return findByString(string, SELECT_INSTRUCTOR_BY_NAME);
    }

    @Override
    public Optional<Instructor> findEntityById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSTRUCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createInstructor(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                            , resultSet.getString(PASSWORD_LABEL), resultSet.getString(URL_LABEL)
                            , resultSet.getString(INFO_LABEL)));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        if (id.equals(6)) {
            return true;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSTRUCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Instructor entity) {
        if (entity.getLogin().equals(ADMIN)) {
            return true;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSTRUCTOR)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Optional<Instructor> create(Instructor entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRUCTOR)) {
            configureStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
            return Optional.empty();
        }
        return findByLogin(entity.getLogin());
    }

    @Override
    public void update(Instructor entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSTRUCTOR)) {
            configureUpdateStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    private void configureStatement(Instructor entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPassword());
    }

    private void configureUpdateStatement(Instructor entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getImgUrl());
        preparedStatement.setString(4, entity.getInfo());
        preparedStatement.setInt(5, entity.getId());
    }

    private Optional<Instructor> findByString(String string, String selectInstructorByString) {
        try (PreparedStatement statement = connection.prepareStatement(selectInstructorByString)) {
            statement.setString(1, string);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(EntityManager.ENTITY_MANAGER.createInstructor(resultSet.getInt(ID_LABEL)
                            , resultSet.getString(LOGIN_LABEL), resultSet.getString(NAME_LABEL)
                            , resultSet.getString(PASSWORD_LABEL), resultSet.getString(URL_LABEL)
                            , resultSet.getString(INFO_LABEL)));
                }
            }
        } catch (SQLException exception) {
            LOGGER.error(exception);
        }
        return Optional.empty();
    }
}
