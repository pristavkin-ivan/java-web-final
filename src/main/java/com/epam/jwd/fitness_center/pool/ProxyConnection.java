package com.epam.jwd.fitness_center.pool;

import java.sql.Connection;

public interface ProxyConnection extends Connection {

    void closeConnection();

}
