package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.model.entity.Instructor;

import java.util.List;
import java.util.Optional;

/**
 * Service that is responsible for instructor's business logic.
 */
public interface InstructorService<T extends InstructorDTO> {

    /**
     * Method that retrieves all saved instructors.
     *
     * @return List of instructor's DTO
     */
    List<T> findAllInstructors();

    /**
     * Method that searches for instructor with appropriate id.
     *
     * @param id
     * @return Optional of instructorDTO
     */
    Optional<T> findInstructorById(Integer id);

    /**
     * Method that authorizes the instructor.
     *
     * @param login
     * @param password
     * @return Not empty optional if log in operation was successful
     */
    Optional<T> login(String login, String password);

    /**
     * Method that registers instructor.
     *
     * @param login
     * @param password
     * @param name
     * @return Not empty optional if sign up operation was successful
     */
    Optional<T> signup(String login, String name, String password) throws SignupException;

    /**
     * Method that updates instructor's profile.
     *
     * @param instructor
     */
    void updateProfile(Instructor instructor);

    /**
     * Method that deletes instructor's profile
     *
     * @param id
     */
    void deleteProfile(Integer id);
}
