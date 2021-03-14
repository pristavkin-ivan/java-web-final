package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.UserDAO;
import com.epam.jwd.fitness_center.dao.impl.ClientDAO;
import com.epam.jwd.fitness_center.dao.impl.InstructorDAO;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.model.entity.Client;
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

public final class InstructorServiceImpl implements InstructorService {

    private static final InstructorServiceImpl INSTRUCTOR_SERVICE = new InstructorServiceImpl();

    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);

    private static final String SIGNUP_EXCEPTION_MESSAGE = "Sign up error: such user exists!";

    private InstructorServiceImpl() {
    }

    @Override
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = null;

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            UserDAO<Instructor> dao = new InstructorDAO(connection);

            instructors = dao.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert instructors != null;
        return instructors.stream().skip(1).map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<InstructorDTO> getInstructorById(Integer id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            UserDAO<Instructor> dao = new InstructorDAO(connection);

            return dao.findEntityById(id).map(this::convertToDto);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<InstructorDTO> login(String login, String password) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final UserDAO<Instructor> dao = new InstructorDAO(connection);
            final Optional<Instructor> instructor = dao.findByString(login);

            if (instructor.isPresent()) {
                if (BCrypt.checkpw(password, instructor.get().getPassword())) {
                    return instructor.map(this::convertToDto);
                }
            } else {
                BCrypt.checkpw(password, "dummyPass"); //to prevent timing attack
            }

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void signup(String login, String name, String password) throws SignupException {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final UserDAO<Instructor> dao = new InstructorDAO(connection);

            if (dao.findByString(login).isPresent()) {
                throw new SignupException(SIGNUP_EXCEPTION_MESSAGE);
            }

            dao.create(EntityManager.ENTITY_MANAGER.createInstructor(0, login, name
                    , BCrypt.hashpw(password, BCrypt.gensalt()), null, null, null));
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void updateProfile(Instructor instructor) {
        try(final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            UserDAO<Instructor> dao = new InstructorDAO(connection);

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
