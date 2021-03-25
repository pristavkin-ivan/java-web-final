package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.exception.NoSuchPurposeException;
import com.epam.jwd.fitness_center.model.dto.EquipmentDTO;
import com.epam.jwd.fitness_center.model.dto.ExerciseDTO;
import com.epam.jwd.fitness_center.model.dto.FoodDTO;

import java.util.List;

public interface PurposeService {

    void deletePurpose(Integer id);

    void createPurpose(Integer trainingId, String exercise, String equipment, String food) throws NoSuchPurposeException;

    void updatePurpose(Integer purposeId, String exercise, String equipment, String food) throws NoSuchPurposeException;

    List<ExerciseDTO> getAllExercises();

    List<EquipmentDTO> getAllEquipment();

    List<FoodDTO> getAllFood();

}
