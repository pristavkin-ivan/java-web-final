package com.epam.jwd.fitness_center.model.entity;

public final class Exercise implements Entity {

    private final String name;

    private final Integer difficulty, repetitions;

    Exercise(String name, Integer difficulty, Integer repetitions) {
        this.name = name;
        this.difficulty = difficulty;
        this.repetitions = repetitions;
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
