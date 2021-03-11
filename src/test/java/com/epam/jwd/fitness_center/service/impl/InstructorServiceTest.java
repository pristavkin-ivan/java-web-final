package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class InstructorServiceTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String ADMIN = "admin";

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void SignUpInstructorTest_CreateInstructor_NotThrowSignUpException() {
        assertDoesNotThrow( () -> InstructorServiceImpl.getInstance()
                .signup(ADMIN,ADMIN, ADMIN));
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }


}
