package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.ClientDAO;
import com.epam.jwd.fitness_center.dao.impl.DAOManager;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
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
import java.util.stream.Collectors;

public final class ClientServiceImpl implements ClientService<ClientDTO> {

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static final ClientServiceImpl CLIENT_SERVICE = new ClientServiceImpl();

    private final static DAOManager DAO_MANAGER = DAOManager.INSTANCE;

    private static final String SIGNUP_EXCEPTION_MESSAGE = "Sign up error: such user exists!";
    private static final String DUMMY_PASSWORD = "dummyPass";

    private ClientServiceImpl() {
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = null;

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);

            clients = dao.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert clients != null;
        return clients.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> getClientById(Integer id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);

            return dao.findEntityById(id).map(this::convertToDto);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientDTO> login(String login, String password) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);
            final Optional<Client> client = dao.findByLogin(login);

            if (client.isPresent()) {
                if (BCrypt.checkpw(password, client.get().getPassword())) {
                    return client.map(this::convertToDto);
                }
            } else {
                BCrypt.checkpw(password, DUMMY_PASSWORD);
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientDTO> signup(String login, String name, String password) throws SignupException {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);

            if (dao.findByLogin(login).isPresent()) {
                throw new SignupException(SIGNUP_EXCEPTION_MESSAGE);
            }
            return dao.create(Client.getBuilder()
                    .login(login)
                    .name(name)
                    .password(BCrypt.hashpw(password, BCrypt.gensalt()))
                    .build()).map(this::convertToDto);

        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void updateProfile(Client client) {
        try(final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);

            dao.update(client);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void deleteProfile(Integer id) {
        try(final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            ClientDAO<Client> dao = DAO_MANAGER.createClientDAO(connection);

            dao.delete(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static ClientServiceImpl getInstance() {
        return CLIENT_SERVICE;
    }

    private ClientDTO convertToDto(Client client) {
        return DTOManager.DTO_MANAGER.createClientDTO(client.getId(), client.getLogin(), client.getName()
                , client.getHeight(), client.getWeight(), client.getBalance());
    }

}
