package com.epam.jwd.fitness_center.model.entity;

import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;

import java.util.List;

public enum EntityManager {
    ENTITY_MANAGER;

    public Client createClient(Integer id, String login, String name, String password, Double height, Double weight) {
        return new Client(id, login, name, password, height, weight, null);
    }

    public Instructor createInstructor(Integer id, String login, String name, String password, List<Training> trainings
            , String url, String info) {
        return new Instructor(id, login, name, password, trainings, url, info);
    }

}
