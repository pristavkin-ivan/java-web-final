package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientServiceTest {

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    private static final String TEST = "test";

    private static final String LOG = "vane4k";

    private static final String PASS = "12345678";

    private static final String INC_PASS = "incpass";

    private final ClientServiceImpl clientService = ClientServiceImpl.getInstance();

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void SignUpClientTest_MustCreateClientIfParamsAreCorrect_NotThrowSignUpException() {
        assertDoesNotThrow(() -> clientService.signup(TEST, TEST, TEST));
    }

    @Test
    public void SignUpClientTest_MustThrowExceptionIfParamsAreIncorrect_ThrowSignUpException() {
        assertThrows(SignupException.class, () -> clientService.signup("vane4k", TEST, TEST));
    }

    @Test
    public void FindAllClientsTest_MustRetrieveAllSavedClients_NotEmptyList() {
        assertTrue(clientService.findAllClients().size() > 0);
    }

    @Test
    public void FindClientByIdTest_MustRetrieveAppropriateClientIfExist_NotEmptyOptional() {
        assertTrue(clientService.findClientById(18).isPresent());
    }

    @Test
    public void FindClientByIdTest_MustReturnEmptyOptionalIfClientIsNotExist_EmptyOptional() {
        assertFalse(clientService.findClientById(110).isPresent());
    }

    @Test
    public void LogInTest_MustAuthorizeClientIfLoginIsExistAndPasswordIsCorrect_NotEmptyOptional() {
        assertTrue(clientService.login(LOG, PASS).isPresent());
    }

    @Test
    public void LogInTest_MustNotAuthorizeClientIfLoginIsNotExistOrPasswordIsNotCorrect_EmptyOptional() {
        assertFalse(clientService.login(LOG, INC_PASS).isPresent());
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
