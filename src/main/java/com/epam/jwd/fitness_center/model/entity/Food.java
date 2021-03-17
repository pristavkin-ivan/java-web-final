package com.epam.jwd.fitness_center.model.entity;

public class Food implements Entity {

    private final String name;

    private final Integer weight, calories;

    Food(String name, Integer weight, Integer calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                '}';
    }
}
