package com.epam.jwd.fitness_center.model.entity;

public final class Exercise implements Entity {

    private final String name;

    private final Integer difficulty, repetitions, id;

    Exercise(Integer id, String name, Integer difficulty, Integer repetitions) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.repetitions = repetitions;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", repetitions=" + repetitions +
                '}';
    }
}
