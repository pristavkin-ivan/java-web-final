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

public enum ShowAllTrainingsCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "trainingsPage";

    private final static TrainingService TRAINING_SERVICE = TrainingServiceImpl.getInstance();

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return resourceBundle.getString(PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        List<TrainingDTO> trainings = TRAINING_SERVICE.findAll();
        requestContext.setAttribute(Attributes.TRAININGS, trainings);
        return RESPONSE_CONTEXT;
    }

}
