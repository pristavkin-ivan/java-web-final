package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.dao.impl.ClientDAO;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.service.api.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static final ClientServiceImpl CLIENT_SERVICE = new ClientServiceImpl();

    private static final String SIGNUP_EXCEPTION_MESSAGE = "Sign up error: such user exists!";

    private ClientServiceImpl() {
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = null;
        //todo в фуннкциях сервиса будут реализованы транзакции
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            DAO<Client> dao = new ClientDAO(connection);

            clients = dao.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            DAO<Client> dao = new ClientDAO(connection);

            return dao.findEntityById(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientDTO> login(String login, String password) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Client> dao = new ClientDAO(connection);
            final Optional<Client> client = dao.findByString(login);

            if (client.isPresent()) {
                if (BCrypt.checkpw(password, client.get().getPassword())) {
                    return client.map(this::convertToDto);
                }
            } else {
                BCrypt.checkpw(password, "dummyPass"); //to prevent timing attack
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void signup(String login, String name, String password) throws SignupException {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            DAO<Client> dao = new ClientDAO(connection);

            if (dao.findByString(login).isPresent()) {
                throw new SignupException(SIGNUP_EXCEPTION_MESSAGE);
            }
            dao.create(new Client(login, name, BCrypt.hashpw(password, BCrypt.gensalt())));
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static ClientServiceImpl getInstance() {
        return CLIENT_SERVICE;
    }

    private ClientDTO convertToDto(Client client) {
        return new ClientDTO(client.getLogin(), client.getName());
    }

}
