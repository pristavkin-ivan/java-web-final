package com.epam.jwd.fitness_center.model.dto;

public class EquipmentDTO implements DTO {

    private final String name;

    private final Integer difficulty;

    EquipmentDTO(String name, Integer difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return name + '.'  + " Difficulty: " + difficulty + '.';
    }
}
