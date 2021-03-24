package com.epam.jwd.fitness_center.model.dto;

public class FoodDTO implements DTO {

    private final String name;

    private final Integer calories;

    FoodDTO(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return name + '.'  + " Calories: " + calories + '.';
    }

}
