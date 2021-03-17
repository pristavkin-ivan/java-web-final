package com.epam.jwd.fitness_center.service.api;

import com.epam.jwd.fitness_center.model.dto.TrainingDTO;

import java.util.List;

public interface TrainingService {

    List<TrainingDTO> getTrainingsByClientId(Integer clientId);

    List<TrainingDTO> getTrainingsByInstructorId(Integer instructorId);
}
