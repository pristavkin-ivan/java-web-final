package com.epam.jwd.fitness_center.pool;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConnectionPoolTest {

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

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
    public void GetConnectionTest_MustRetrieveConnectionFromPool_Connection() {
        try {
            assertNotNull(ConnectionPool.getConnectionPool().getConnection());
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GetConnectionTest_MustThrowConnectionPoolExceptionIfPoolIsEmpty_ConnectionPoolException() {
        Connection connections[] = new Connection[10];
        assertThrows(ConnectionPoolException.class, () -> {
            for(int i=0; i<10; i++) {
                connections[i] = ConnectionPool.getConnectionPool().getConnection();
            }
        });
    }

    @Test
    public void ReturnConnectionTest_MustMustReturnConnectionToPool_NotThrowException() {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            Connection newConnection = connection;
            assertDoesNotThrow(() -> ConnectionPool.getConnectionPool().returnConnection(newConnection));
        }
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }

}
