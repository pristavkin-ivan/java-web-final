package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.InstructorDAO;
import com.epam.jwd.fitness_center.dao.impl.DAOManager;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.model.entity.EntityManager;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class InstructorServiceImpl implements InstructorService<InstructorDTO> {

    private static final InstructorServiceImpl INSTRUCTOR_SERVICE = new InstructorServiceImpl();

    private final static DAOManager DAO_MANAGER = DAOManager.INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static final String SIGNUP_EXCEPTION_MESSAGE = "Sign up error: such user exists!";
    private static final String DUMMY_PASSWORD = "dummyPass";

    private InstructorServiceImpl() {
    }

    @Override
    public List<InstructorDTO> findAllInstructors() {
        List<Instructor> instructors = null;

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);

            instructors = dao.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert instructors != null;
        return instructors.stream().skip(1).map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProfile(Integer id) {
        try(final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);

            dao.delete(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public Optional<InstructorDTO> findInstructorById(Integer id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);

            return dao.findEntityById(id).map(this::convertToDto);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<InstructorDTO> login(String login, String password) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);
            final Optional<Instructor> instructor = dao.findByLogin(login);

            if (instructor.isPresent()) {
                if (BCrypt.checkpw(password, instructor.get().getPassword())) {
                    return instructor.map(this::convertToDto);
                }
            } else {
                BCrypt.checkpw(password, DUMMY_PASSWORD);
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<InstructorDTO> signup(String login, String name, String password) throws SignupException {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);

            if (dao.findByLogin(login).isPresent()) {
                throw new SignupException(SIGNUP_EXCEPTION_MESSAGE);
            }

            return dao.create(EntityManager.ENTITY_MANAGER.createInstructor(0, login, name
                    , BCrypt.hashpw(password, BCrypt.gensalt()), null, null)).map(this::convertToDto);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void updateProfile(Instructor instructor) {
        try(final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            InstructorDAO<Instructor> dao = DAO_MANAGER.createInstructorDAO(connection);

            dao.update(instructor);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static InstructorServiceImpl getInstance() {
        return INSTRUCTOR_SERVICE;
    }

    private InstructorDTO convertToDto(Instructor instructor) {
        return DTOManager.DTO_MANAGER.createInstructorDTO(instructor.getId(), instructor.getLogin()
                , instructor.getName(), instructor.getImgUrl(), instructor.getInfo());
    }

}
