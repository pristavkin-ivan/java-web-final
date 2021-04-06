package com.epam.jwd.fitness_center.service.api;

/**
 * Service for calculating daily calories
 */
public interface CaloriesService {

    /**
     * Calculates daily calories for female
     *
     * @param height
     * @param weight
     * @return calculated calories
     */
    Double calculateFemaleCalories(Double height, Double weight);

    /**
     * Calculates daily calories for male
     *
     * @param height
     * @param weight
     * @return calculated calories
     */
    Double calculateMaleCalories(Double height, Double weight);

}
