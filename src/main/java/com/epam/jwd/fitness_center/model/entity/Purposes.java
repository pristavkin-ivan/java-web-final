package com.epam.jwd.fitness_center.model.entity;

import java.util.List;

public final class Purposes implements Entity {

    private final Integer trainingId;

    private final List<Exercise> exercises;

    private final List<Equipment> equipment;

    private final List<Food> foods;

    Purposes(Integer trainingId, List<Exercise> exercises, List<Equipment> equipment, List<Food> foods) {
        this.trainingId = trainingId;
        this.exercises = exercises;
        this.equipment = equipment;
        this.foods = foods;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public String toString() {
        return "Purposes{" +
                "exercises=" + exercises +
                ", equipment=" + equipment +
                ", foods=" + foods +
                '}';
    }
}
