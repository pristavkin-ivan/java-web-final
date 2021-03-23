package com.epam.jwd.fitness_center.model.entity;

public final class Equipment implements Entity {

    private final String name;

    private final Integer difficulty, id;

    Equipment(Integer id, String name, Integer difficulty) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
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

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
