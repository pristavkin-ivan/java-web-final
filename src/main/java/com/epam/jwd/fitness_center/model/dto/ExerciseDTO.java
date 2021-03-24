package com.epam.jwd.fitness_center.model.dto;

public class ExerciseDTO implements DTO {

    private final String name;

    private final Integer difficulty, repetitions;

    ExerciseDTO(String name, Integer difficulty, Integer repetitions) {
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
        return name + '.'  + " Difficulty: " + difficulty + '.' + " Repetitions: " + difficulty + '.';
    }

}
