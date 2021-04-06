package com.epam.jwd.fitness_center.service.impl.calories;

import com.epam.jwd.fitness_center.service.api.CaloriesService;

public enum NormalWeightCaloriesService implements CaloriesService {
    INSTANCE;

    @Override
    public Double calculateFemaleCalories(Double height, Double weight) {
        return 655.1 + (9.563 * weight) + (1.85 * height) - 112.224;
    }

    @Override
    public Double calculateMaleCalories(Double height, Double weight) {
        return 66.5 + (13.75 * weight) + (5.003 * height) - 162.6;
    }
}
