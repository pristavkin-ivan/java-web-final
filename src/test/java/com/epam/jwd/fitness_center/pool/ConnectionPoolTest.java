package com.epam.jwd.fitness_center.pool;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConnectionPoolTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
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
