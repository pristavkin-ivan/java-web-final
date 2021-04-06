package com.epam.jwd.fitness_center.service.impl.calories;

import com.epam.jwd.fitness_center.service.api.CaloriesService;

public enum GainWeightCaloriesService implements CaloriesService {
    INSTANCE;

    @Override
    public Double calculateFemaleCalories(Double height, Double weight) {
        return NormalWeightCaloriesService.INSTANCE.calculateFemaleCalories(height, weight) * 1.2;
    }

    @Override
    public Double calculateMaleCalories(Double height, Double weight) {
        return NormalWeightCaloriesService.INSTANCE.calculateMaleCalories(height, weight) * 1.2;
    }
}
