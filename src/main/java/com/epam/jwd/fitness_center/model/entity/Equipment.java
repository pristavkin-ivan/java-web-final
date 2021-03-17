package com.epam.jwd.fitness_center.model.entity;

public final class Equipment implements Entity {

    private final String name;

    private final Integer difficulty;

    Equipment(String name, Integer difficulty) {
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
        return "Equipment{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
