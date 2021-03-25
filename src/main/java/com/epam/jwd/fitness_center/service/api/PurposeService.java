package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.NoSuchPurposeException;
import com.epam.jwd.fitness_center.model.dto.EquipmentDTO;
import com.epam.jwd.fitness_center.model.dto.ExerciseDTO;
import com.epam.jwd.fitness_center.model.dto.FoodDTO;

import java.util.List;

/**
 * Service that is responsible for purpose's business logic.
 */
public interface PurposeService {

    /**
     * Deletes purpose with appropriate id
     *
     * @param id
     */
    void deletePurpose(Integer id);

    /**
     * Creates purpose
     *
     * @param trainingId
     * @param exercise
     * @param equipment
     * @param food
     * @throws NoSuchPurposeException
     */
    void createPurpose(Integer trainingId, String exercise, String equipment, String food) throws NoSuchPurposeException;

    /**
     * Updates purpose
     *
     * @param purposeId
     * @param exercise
     * @param equipment
     * @param food
     * @throws NoSuchPurposeException
     */
    void updatePurpose(Integer purposeId, String exercise, String equipment, String food) throws NoSuchPurposeException;

    /**
     * Retrieves all available exercises
     *
     * @return List of ExerciseDTO
     */
    List<ExerciseDTO> getAllExercises();

    /**
     * Retrieves all available equipment
     *
     * @return List of EquipmentDTO
     */
    List<EquipmentDTO> getAllEquipment();

    /**
     * Retrieves all available food
     *
     * @return List of FoodDTO
     */
    List<FoodDTO> getAllFood();

}
