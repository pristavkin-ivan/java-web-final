package com.epam.jwd.fitness_center.model.entity;

public class Food implements Entity {

    private final String name;

    private final Integer weight, calories, id;

    Food(Integer id, String name, Integer weight, Integer calories) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
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
