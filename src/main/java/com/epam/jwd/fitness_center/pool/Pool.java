package com.epam.jwd.fitness_center.pool;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Pool {

    Connection getConnection() throws ConnectionPoolException, SQLException;

    void returnConnection(Connection connection) throws ConnectionPoolException;

    void init(String url, String user, String password) throws ConnectionPoolException, SQLException;

    void destroyConnectionPool();

}
