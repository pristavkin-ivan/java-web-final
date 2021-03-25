package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.model.entity.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService<T extends InstructorDTO> {

    List<T> getAllInstructors();

    Optional<T> getInstructorById(Integer id);

    Optional<T> login(String login, String password);

    Optional<T> signup(String login, String name, String password) throws SignupException;

    void updateProfile(Instructor instructor);

    void deleteProfile(Integer id);
}
