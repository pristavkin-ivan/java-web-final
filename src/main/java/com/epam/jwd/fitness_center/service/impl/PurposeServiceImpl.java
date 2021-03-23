package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.PurposesDAO;
import com.epam.jwd.fitness_center.dao.impl.PurposesDAOImpl;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.service.api.PurposeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public final class PurposeServiceImpl implements PurposeService {

    private final static Logger LOGGER = LogManager.getLogger(PurposesDAOImpl.class);

    private final static PurposeServiceImpl SERVICE = new PurposeServiceImpl();

    private PurposeServiceImpl() {
    }

    @Override
    public void deletePurpose(Integer id) {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final PurposesDAO purposesDAO = new PurposesDAOImpl(connection);

            purposesDAO.delete(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static PurposeServiceImpl getInstance() {
        return SERVICE;
    }

}
