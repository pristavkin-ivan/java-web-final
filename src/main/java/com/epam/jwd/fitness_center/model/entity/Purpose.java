package com.epam.jwd.fitness_center.model.entity;

public final class Purpose implements Entity {

    private final Integer trainingId, id;

    private final Exercise exercise;

    private final Equipment equipment;

    private final Food food;

    Purpose(Integer id, Integer trainingId, Exercise exercise, Equipment equipment, Food food) {
        this.id = id;
        this.trainingId = trainingId;
        this.exercise = exercise;
        this.equipment = equipment;
        this.food = food;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Purpose{" +
                "exercises=" + exercise +
                ", equipment=" + equipment +
                ", foods=" + food +
                '}';
    }
}
