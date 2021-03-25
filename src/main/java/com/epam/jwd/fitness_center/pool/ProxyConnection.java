package com.epam.jwd.fitness_center.pool;

import java.sql.Connection;

/**
 * Proxy for Connection
 */
public interface ProxyConnection extends Connection {

    /**
     * Closes connection
     */
    void closeConnection();

}
