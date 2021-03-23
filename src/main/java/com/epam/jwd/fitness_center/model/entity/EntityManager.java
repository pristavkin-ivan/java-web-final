package com.epam.jwd.fitness_center.model.entity;

import java.util.List;

public enum EntityManager {
    ENTITY_MANAGER;

    public Client createClient(Integer id, Integer amountOfTrainings, String login, String name, String password, Double height, Double weight
            , Double balance) {
        return new Client(id, amountOfTrainings, login, name, password, height, weight, balance);
    }

    public Instructor createInstructor(Integer id, String login, String name, String password, String url
            , String info) {
        return new Instructor(id, login, name, password, url, info);
    }

    public Training createTraining(Integer id, Client client, Instructor instructor, Integer amount, Integer difficulty
            , List<Purpose> purposes, Double price, String comment) {
        return new Training(id, client, instructor, amount, difficulty, purposes, price, comment);
    }

    public Purpose createPurposes(Integer id, Integer trainingId, Exercise exercise, Equipment equipment, Food food) {
        return new Purpose(id, trainingId, exercise, equipment, food);
    }

    public Exercise createExercise(Integer id, String name, Integer difficulty, Integer repetitions) {
        return new Exercise(id, name, difficulty, repetitions);
    }

    public Equipment createEquipment(Integer id, String name, Integer difficulty) {
        return new Equipment(id, name, difficulty);
    }

    public Food createFood(Integer id, String name, Integer weight, Integer calories) {
        return new Food(id, name, weight, calories);
    }

}
