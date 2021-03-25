package com.epam.jwd.fitness_center.pool;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool implements Pool {

    private final static ConnectionPool CONNECTION_POOL = new ConnectionPool();

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String LIMIT_REACHED = "Can't get connection: limit reached!";
    private static final String POOL_IS_FULL = "Error: connection pool is full";

    private ReentrantLock lock = new ReentrantLock();

    private final static Stack<ProxyConnection> CONNECTION_STACK = new Stack<>();

    private final static int MAX_CONNECTIONS = 8;

    private static int amountOfAvailableConnections = MAX_CONNECTIONS;

    private ConnectionPool() {
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException, SQLException {
        lock.lock();
        if (CONNECTION_STACK.isEmpty()) {
            lock.unlock();
            throw new ConnectionPoolException(LIMIT_REACHED);
        }
        lock.unlock();
        return CONNECTION_STACK.pop();
    }

    @Override
    public void returnConnection(Connection connection) throws ConnectionPoolException {
        lock.lock();
        if (CONNECTION_STACK.size() <= MAX_CONNECTIONS) {
            CONNECTION_STACK.push((ProxyConnection) connection);
        } else {
            lock.unlock();
            throw new ConnectionPoolException(POOL_IS_FULL);
        }
        lock.unlock();
    }

    @Override
    public void init(String url, String user, String password) throws ConnectionPoolException, SQLException {
        registerDrivers(url);
        for(int i=0; i< MAX_CONNECTIONS; i++) {
            CONNECTION_STACK.push(new ProxyConnectionImpl(DriverManager.getConnection(url, user, password)));
        }
    }

    @Override
    public void destroyConnectionPool() {
        CONNECTION_STACK.forEach(ProxyConnection::closeConnection);
        deregisterDrivers();
    }

    public static void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public static ConnectionPool getConnectionPool() {
        return CONNECTION_POOL;
    }

    private static void registerDrivers(String url) {
        try {
            Class.forName(CLASS_NAME);
            DriverManager.registerDriver(DriverManager.getDriver(url));
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
    }

}
