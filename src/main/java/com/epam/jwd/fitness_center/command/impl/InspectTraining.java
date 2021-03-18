package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import com.epam.jwd.fitness_center.service.impl.TrainingServiceImpl;

import java.util.Optional;
import java.util.ResourceBundle;

public enum InspectTraining implements Command {
    INSTANCE;

    private static final String PAGE_KEY = "trainingPage";
    private static final String BUNDLE_NAME = "pages";

    private static final TrainingService TRAINING_SERVICE = TrainingServiceImpl.getInstance();

    private final static ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

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
        final Optional<TrainingDTO> optionalTrainingDTO
                = TRAINING_SERVICE.findTrainingById(Integer.parseInt(requestContext.getParameter(Attributes.TRAINING_ID)));

        optionalTrainingDTO.ifPresent(dto -> requestContext.setAttribute(Attributes.TRAINING, dto));

        return RESPONSE_CONTEXT;
    }
}
