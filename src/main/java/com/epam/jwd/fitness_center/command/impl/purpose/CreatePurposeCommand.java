package com.epam.jwd.fitness_center.command.impl.purpose;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.exception.NoSuchPurposeException;
import com.epam.jwd.fitness_center.model.dto.EquipmentDTO;
import com.epam.jwd.fitness_center.model.dto.ExerciseDTO;
import com.epam.jwd.fitness_center.model.dto.FoodDTO;
import com.epam.jwd.fitness_center.service.api.PurposeService;
import com.epam.jwd.fitness_center.service.impl.PurposeServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;

import java.util.List;
import java.util.ResourceBundle;

public enum CreatePurposeCommand implements Command {
    INSTANCE;

    private static final String PAGE_KEY = "createPurposePage";
    private static final String BUNDLE_NAME = "pages";
    private static final String COMMAND_KEY = "command.inspectTraining";

    private static final PurposeService PURPOSE_SERVICE = PurposeServiceImpl.getInstance();

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        private boolean redirect = false;

        @Override
        public String getPage() {
            return resourceBundle.getString(PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return redirect;
        }

        @Override
        public void setRedirect(boolean redirect) {
            this.redirect = redirect;
        }

        @Override
        public String getCommand() {
            return resourceBundle.getString(COMMAND_KEY);
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String exercise = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.EXERCISE)));
        final String equipment = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.EQUIPMENT)));
        final String food = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.FOOD)));
        final String trainingId = requestContext.getParameter(Attributes.TRAINING_ID);

        RESPONSE_CONTEXT.setRedirect(false);

        setAttributes(requestContext);

        ResponseContext responseContext = validate(requestContext, exercise, equipment, food, trainingId);
        if (responseContext != null) return responseContext;

        requestContext.setSessionAttribute(Attributes.TRAINING_ID, trainingId);
        RESPONSE_CONTEXT.setRedirect(true);
        return RESPONSE_CONTEXT;
    }

    private ResponseContext validate(RequestContext requestContext, String exercise, String equipment, String food, String trainingId) {
        if (exercise.equals("") && equipment.equals("") && food.equals("") || equipment.equals(Attributes.NULL)
                && exercise.equals(Attributes.NULL) && food.equals(Attributes.NULL)) {
            return RESPONSE_CONTEXT;
        }

        try {
            PURPOSE_SERVICE.createPurpose(Integer.parseInt(trainingId), exercise, equipment, food);
        } catch (NoSuchPurposeException e) {
            requestContext.setAttribute(Attributes.ERROR, e.getMessage());
            return RESPONSE_CONTEXT;
        }
        return null;
    }

    private void setAttributes(RequestContext requestContext) {
        final List<EquipmentDTO> equipments = PURPOSE_SERVICE.getAllEquipment();
        final List<FoodDTO> foods = PURPOSE_SERVICE.getAllFood();
        final List<ExerciseDTO> exercises = PURPOSE_SERVICE.getAllExercises();

        requestContext.setAttribute(Attributes.EXERCISES, exercises);
        requestContext.setAttribute(Attributes.EQUIPMENTS, equipments);
        requestContext.setAttribute(Attributes.FOODS, foods);
    }

}
