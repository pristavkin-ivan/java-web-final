package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.dao.api.DAO;
import com.epam.jwd.fitness_center.dao.api.PurposesDAO;
import com.epam.jwd.fitness_center.dao.impl.EquipmentDAO;
import com.epam.jwd.fitness_center.dao.impl.ExerciseDAO;
import com.epam.jwd.fitness_center.dao.impl.FoodDAO;
import com.epam.jwd.fitness_center.dao.impl.PurposesDAOImpl;
import com.epam.jwd.fitness_center.exception.ConnectionPoolException;
import com.epam.jwd.fitness_center.exception.NoSuchPurposeException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.DTOManager;
import com.epam.jwd.fitness_center.model.dto.EquipmentDTO;
import com.epam.jwd.fitness_center.model.dto.ExerciseDTO;
import com.epam.jwd.fitness_center.model.dto.FoodDTO;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Equipment;
import com.epam.jwd.fitness_center.model.entity.Exercise;
import com.epam.jwd.fitness_center.model.entity.Food;
import com.epam.jwd.fitness_center.pool.ConnectionPool;
import com.epam.jwd.fitness_center.service.api.PurposeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public final class PurposeServiceImpl implements PurposeService {

    private final static Logger LOGGER = LogManager.getLogger(PurposesDAOImpl.class);

    private final static PurposeServiceImpl SERVICE = new PurposeServiceImpl();

    private final static String NO_SUCH_EXERCISE = "Error: no such exercise!";
    private final static String NO_SUCH_EQUIPMENT = "Error: no such equipment!";
    private final static String NO_SUCH_FOOD = "Error: no such food!";

    private PurposeServiceImpl() {
    }

    @Override
    public void deletePurpose(Integer id) {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final PurposesDAO purposesDAO = new PurposesDAOImpl(connection);

            purposesDAO.delete(id);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void createPurpose(Integer trainingId, String exercise, String equipment, String food) throws NoSuchPurposeException {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Exercise> exerciseDAO = new ExerciseDAO(connection);
            final DAO<Equipment> equipmentDAO = new EquipmentDAO(connection);
            final DAO<Food> foodDAO = new FoodDAO(connection);
            final PurposesDAO purposesDAO = new PurposesDAOImpl(connection);

            makePurpose(trainingId, exercise, equipment, food, exerciseDAO, equipmentDAO, foodDAO, purposesDAO);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public void updatePurpose(Integer purposeId, String exercise, String equipment, String food) throws NoSuchPurposeException {
        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Exercise> exerciseDAO = new ExerciseDAO(connection);
            final DAO<Equipment> equipmentDAO = new EquipmentDAO(connection);
            final DAO<Food> foodDAO = new FoodDAO(connection);
            final PurposesDAO purposesDAO = new PurposesDAOImpl(connection);

           updatePurpose(purposeId, exercise, equipment, food, exerciseDAO, equipmentDAO, foodDAO, purposesDAO);
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> exercises = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Exercise> exerciseDAO = new ExerciseDAO(connection);

            exercises = exerciseDAO.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert exercises != null;
        return exercises.stream().map(this::convertExerciseToDto).collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<Equipment> equipment = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Equipment> equipmentDAO = new EquipmentDAO(connection);

            equipment = equipmentDAO.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert equipment != null;
        return equipment.stream().map(this::convertEquipmentToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FoodDTO> getAllFood() {
        List<Food> food = null;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection()) {
            final DAO<Food> foodDAO = new FoodDAO(connection);

            food = foodDAO.findAll();
        } catch (SQLException | ConnectionPoolException exception) {
            LOGGER.error(exception.getMessage());
        }

        assert food != null;
        return food.stream().map(this::convertFoodToDTO).collect(Collectors.toList());
    }

    private void makePurpose(Integer trainingId, String exercise, String equipment, String food
            , DAO<Exercise> exerciseDAO, DAO<Equipment> equipmentDAO, DAO<Food> foodDAO
            , PurposesDAO purposesDAO) throws NoSuchPurposeException {

        Integer exerciseId, equipmentId, foodId;

        exerciseId = exerciseDAO
                .getEntityByName(exercise).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_EXERCISE)).getId();
        equipmentId = equipmentDAO
                .getEntityByName(equipment).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_EQUIPMENT)).getId();
        foodId = foodDAO
                .getEntityByName(food).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_FOOD)).getId();
        purposesDAO.addPurpose(trainingId, exerciseId, equipmentId, foodId);
    }

    private void updatePurpose(Integer purposeId, String exercise, String equipment, String food
            , DAO<Exercise> exerciseDAO, DAO<Equipment> equipmentDAO, DAO<Food> foodDAO
            , PurposesDAO purposesDAO) throws NoSuchPurposeException {

        Integer exerciseId, equipmentId, foodId;

        exerciseId = exerciseDAO
                .getEntityByName(exercise).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_EXERCISE)).getId();
        equipmentId = equipmentDAO
                .getEntityByName(equipment).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_EQUIPMENT)).getId();
        foodId = foodDAO
                .getEntityByName(food).orElseThrow(() -> new NoSuchPurposeException(NO_SUCH_FOOD)).getId();
        purposesDAO.update(purposeId, exerciseId, equipmentId, foodId);
    }

    public static PurposeServiceImpl getInstance() {
        return SERVICE;
    }

    private ExerciseDTO convertExerciseToDto(Exercise exercise) {
        return DTOManager.DTO_MANAGER.createExerciseDTO(exercise.getName(), exercise.getDifficulty()
                , exercise.getRepetitions());
    }

    private EquipmentDTO convertEquipmentToDTO(Equipment equipment) {
        return DTOManager.DTO_MANAGER.createEquipmentDTO(equipment.getName(), equipment.getDifficulty());
    }

    private FoodDTO convertFoodToDTO(Food food) {
        return DTOManager.DTO_MANAGER.createFoodDTO(food.getName(), food.getCalories());
    }

}
