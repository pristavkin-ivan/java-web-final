package com.epam.jwd.fitness_center.pool;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool interface
 */
public interface Pool {

    /**
     * Retrieves connection from pool
     *
     * @return Connection
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    Connection getConnection() throws ConnectionPoolException, SQLException;

    /**
     * Returns connection to pool
     *
     * @param connection
     * @throws ConnectionPoolException
     */
    void returnConnection(Connection connection) throws ConnectionPoolException;

    /**
     * Initialize connection pool
     * @param url
     * @param user
     * @param password
     * @throws ConnectionPoolException
     * @throws SQLException
     */
    void init(String url, String user, String password) throws ConnectionPoolException, SQLException;

    /**
     * Destroys connection pool
     */
    void destroyConnectionPool();

}
