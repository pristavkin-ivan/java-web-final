package com.epam.jwd.fitness_center.model.entity;

import java.util.List;

public enum EntityManager {
    ENTITY_MANAGER;

    public Client createClient(Integer id, String login, String name, String password, Double height, Double weight
            , Double balance) {
        return new Client(id, login, name, password, height, weight, balance);
    }

    public Instructor createInstructor(Integer id, String login, String name, String password, String url
            , String info) {
        return new Instructor(id, login, name, password, url, info);
    }

    public Training createTraining(Integer id, Client client, Instructor instructor, Integer amount, Integer difficulty
            , Purposes purposes, Double price) {
        return new Training(id, client, instructor, amount, difficulty, purposes, price);
    }

    public Purposes createPurposes(Integer trainingId, List<Exercise> exercises, List<Equipment> equipment
            , List<Food> foods) {
        return new Purposes(trainingId, exercises, equipment, foods);
    }

    public Exercise createExercise(String name, Integer difficulty, Integer repetitions) {
        return new Exercise(name, difficulty, repetitions);
    }

    public Equipment createEquipment(String name, Integer difficulty) {
        return new Equipment(name, difficulty);
    }

    public Food createFood(String name, Integer weight, Integer calories) {
        return new Food(name, weight, calories);
    }

}
