package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientServiceTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String TEST = "test";

    private final ClientServiceImpl clientService = ClientServiceImpl.getInstance();

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void SignUpClientTest_MustCreateClient_NotThrowSignUpException() {
        assertDoesNotThrow(() -> clientService.signup(TEST, TEST, TEST));
    }

    @Test
    public void FindAllClientsTest_MustRetrieveAllSavedClients_NotEmptyList() {
        assertTrue(clientService.findAllClients().size() > 0);
    }

    @Test
    public void FindClientByIdTest_MustRetrieveAppropriateClient_NotEmptyOptional() {
        assertTrue(clientService.findClientById(18).isPresent());
    }

    @Test
    public void LogInTest_MustAuthorizeInstructor_NotEmptyOptional() {
        assertTrue(clientService.login(TEST, TEST).isPresent());
    }

    @Test
    public void UpdateProfileTest_MustUpdateClient() {
        clientService.updateProfile(Client.getBuilder()
                .id(38)
                .name("15")
                .login(TEST)
                .weight(0.0)
                .height(0.0)
                .build());
    }

    @Test
    public void DeleteProfileTest_MustDeleteInstructor() {
        clientService.deleteProfile(38);
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }

}
