package com.epam.jwd.fitness_center.command.impl.training;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import com.epam.jwd.fitness_center.service.impl.TrainingServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public enum ShowTrainingsCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "trainingsPage";
    private final static String ERROR_COMMAND_KEY = "command.error";


    private final static TrainingService<TrainingDTO> TRAINING_SERVICE = TrainingServiceImpl.getInstance();

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

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        List<TrainingDTO> trainings;
        final Integer id = (Integer) requestContext.getSessionAttribute(Attributes.ID);

        if (Objects.equals(requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR), true)) {
            trainings = TRAINING_SERVICE.findTrainingsByInstructorId(id);
        } else {
            trainings = TRAINING_SERVICE.findTrainingsByClientId(id);
        }

        requestContext.setAttribute(Attributes.TRAININGS, trainings);
        return RESPONSE_CONTEXT;
    }

}
