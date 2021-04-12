package com.epam.jwd.fitness_center.listener;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebListener
public final class ApplicationListener implements ServletContextListener {

    private final static Logger LOGGER = LogManager.getLogger(ApplicationListener.class.getName());

    private final static String INIT_MESSAGE = "ServletContext and connection pool were initialized.";

    private final static String ERROR_MESSAGE = "Connection pool wasn't initialized.";

    private final static String DESTROY_MESSAGE = "ServletContext and connection pool were destroyed.";

    private static final String DATABASE = "database";

    private final static ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getConnectionPool().init(DATABASE_BUNDLE.getString(Attributes.URL)
                    , DATABASE_BUNDLE.getString(Attributes.USER), DATABASE_BUNDLE.getString(Attributes.PASSWORD));
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
