package com.epam.jwd.fitness_center.model.dto;

import com.epam.jwd.fitness_center.model.entity.Purpose;

import java.util.List;

public enum DTOManager {
    DTO_MANAGER;

    public ClientDTO createClientDTO(Integer id, String login, String name, Double height, Double weight, Double balance) {
        return new ClientDTO(id, login, name, height, weight, balance);
    }

    public InstructorDTO createInstructorDTO(Integer id, String login, String name, String url, String info) {
        return new InstructorDTO(id, login, name, url, info);
    }

    public TrainingDTO createTrainingDTO(Integer id, String instructorName, String clientName, String instructorPhotoUrl
            , Integer amount, Integer difficulty, List<Purpose> purposes, Double price, String comment) {
        return new TrainingDTO(id, instructorName, clientName, instructorPhotoUrl, amount, difficulty, purposes, price
                , comment);
    }

    public ExerciseDTO createExerciseDTO(String name, Integer difficulty, Integer repetitions) {
        return new ExerciseDTO(name, difficulty, repetitions);
    }

    public EquipmentDTO createEquipmentDTO(String name, Integer difficulty) {
        return new EquipmentDTO(name, difficulty);
    }

    public FoodDTO createFoodDTO(String name, Integer calories) {
        return new FoodDTO(name, calories);
    }

}
