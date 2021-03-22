package com.epam.jwd.fitness_center.model.dto;

import com.epam.jwd.fitness_center.model.entity.Purposes;

public enum DTOManager {
    DTO_MANAGER;

    public ClientDTO createClientDTO(Integer id, String login, String name, Double height, Double weight, Double balance) {
        return new ClientDTO(id, login, name, height, weight, balance);
    }

    public InstructorDTO createInstructorDTO(Integer id, String login, String name, String url, String info) {
        return new InstructorDTO(id, login, name, url, info);
    }

    public TrainingDTO createTrainingDTO(Integer id, String instructorName, String clientName, String instructorPhotoUrl
            , Integer amount, Integer difficulty, Purposes purposes, Double price, String comment) {
        return new TrainingDTO(id, instructorName, clientName, instructorPhotoUrl, amount, difficulty, purposes, price
                , comment);
    }

}
