package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorServiceTest {

    public final static String URL = "jdbc:mysql://localhost:3306/fitnessCenterDB?serverTimezone=Europe/Moscow";

    public final static String USER = "root";

    public final static String PASSWORD = "12345678L";

    private static final String ADMIN = "admin";
    private static final String TEST = "test";

    private final InstructorServiceImpl instructorService = InstructorServiceImpl.getInstance();

    private final static String PASS = "admin";
    private final static String INC_PASS = "incpass";
    private final static String S_LOGIN = "swarts";

    @BeforeAll
    static void initConnectionPool() {
        try {
            ConnectionPool.getConnectionPool().init(URL, USER, PASSWORD);
        } catch (SQLException | ConnectionPoolException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void SignUpInstructorTest_MustCreateInstructor_NotThrowSignUpException() {
        assertDoesNotThrow( () -> instructorService.signup(ADMIN,ADMIN, ADMIN));
    }

    @Test
    public void SignUpInstructorTest_MustThrowExceptionIfParamsAreIncorrect_ThrowSignUpException() {
        assertThrows(SignupException.class, () -> instructorService.signup(ADMIN, TEST, TEST));
    }

    @Test
    public void FindAllInstructorsTest_MustRetrieveAllSavedInstructors_NotEmptyList() {
        assertTrue(instructorService.findAllInstructors().size() > 0);
    }

    @Test
    public void FindInstructorByIdTest_MustRetrieveAppropriateInstructorIfInstructorIsExist_NotEmptyOptional() {
        assertTrue(instructorService.findInstructorById(8).isPresent());
    }

    @Test
    public void FindInstructorByIdTest_MustReturnEmptyOptionalIfInstructorIsNotExist_EmptyOptional() {
        assertFalse(instructorService.findInstructorById(110).isPresent());
    }

    @Test
    public void LogInTest_MustAuthorizeInstructorIfInstructorIsExist_NotEmptyOptional() {
        assertTrue(instructorService.login(S_LOGIN, PASS).isPresent());
    }

    @Test
    public void LogInTest_MustNotAuthorizeInstructorIfInstructorIsNotExistOrPasswordIsIncorrect_EmptyOptional() {
        assertFalse(instructorService.login(S_LOGIN, INC_PASS).isPresent());
    }

    @Test
    public void UpdateProfileTest_MustUpdateInstructor() {
       instructorService.updateProfile(Instructor.getBuilder()
               .id(13)
               .name("volodya")
               .login("instr")
               .info("info pro volodyu")
               .build());
    }

    @Test
    public void DeleteProfileTest_MustDeleteInstructor() {
        instructorService.deleteProfile(13);
    }

    @AfterAll
    static void destroyConnectionPool() {
        ConnectionPool.getConnectionPool().destroyConnectionPool();
    }


}
