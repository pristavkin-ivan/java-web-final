package com.epam.jwd.fitness_center.dao.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.model.entity.Instructor;

import java.util.List;
import java.util.Optional;

public class InstructorDAO implements DAO<Instructor> {

    @Override
    public List<Instructor> findAll() {
        return null;
    }

    @Override
    public Optional<Instructor> findByString(String string) {
        return Optional.empty();
    }

    @Override
    public Optional<Instructor> findEntityById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Instructor entity) {
        return false;
    }

    @Override
    public boolean create(Instructor entity) {
        return false;
    }

    @Override
    public Instructor update(Instructor entity) {
        return null;
    }
}
