package com.epam.jwd.fitness_center.listener;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public final class ApplicationListener implements ServletContextListener {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";
    public final static String USER = "root";
    public final static String PASSWORD = "12345678L";

    private final static Logger LOGGER = LogManager.getLogger(ApplicationListener.class.getName());
    private final static String INIT_MESSAGE = "ServletContext and connection pool were initialized.";
    private final static String ERROR_MESSAGE = "Connection pool wasn't initialized.";
    private final static String DESTROY_MESSAGE = "ServletContext and connection pool were destroyed.";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
            LOGGER.info(INIT_MESSAGE);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(ERROR_MESSAGE, exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
        LOGGER.info(DESTROY_MESSAGE);
    }

}
