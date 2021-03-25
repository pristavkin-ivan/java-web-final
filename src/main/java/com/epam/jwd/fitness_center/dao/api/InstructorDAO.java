package com.epam.jwd.fitness_center.dao.api;

import com.epam.jwd.fitness_center.model.entity.Instructor;

import java.util.List;
import java.util.Optional;

/**
 * DAO that is responsible for operating with instructor's database.
 *
 * @param <T> defines instructor this DAO works with.
 */
public interface InstructorDAO<T extends Instructor> {

    /**
     * Method that retrieves all saved instructors from database.
     *
     * @return List of instructors.
     */
    List<T> findAll();

    /**
     * Method that searches for instructor with appropriate login in database.
     *
     * @param string
     * @return Optional of instructor
     */
    Optional<T> findByLogin(String string);

    /**
     * Method that searches for instructor with appropriate name in database.
     *
     * @param string
     * @return Optional of instructor
     */
    Optional<T> findByName(String string);

    /**
     * Method that searches for instructor with appropriate id in database.
     *
     * @param id
     * @return Optional of instructor
     */
    Optional<T> findEntityById(Integer id);

    /**
     * Method that deletes instructor with appropriate id from database.
     *
     * @param id
     * @return boolean
     */
    boolean delete(Integer id);

    /**
     * Method that deletes instructor from database.
     *
     * @param entity
     * @return boolean
     */
    boolean delete(T entity);

    /**
     * Method that creates instructor and adds it to database.
     *
     * @param entity
     * @return Optional of client
     */
    Optional<T> create(T entity);

    /**
     * Method that updates instructor in database.
     *
     * @param entity
     */
    void update(T entity);

}