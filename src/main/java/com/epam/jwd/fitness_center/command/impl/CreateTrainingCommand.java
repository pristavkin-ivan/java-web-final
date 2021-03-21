package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.exception.NoSuchInstructorException;
import com.epam.jwd.fitness_center.exception.NotEnoughMoneyException;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.TrainingServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public enum CreateTrainingCommand implements Command {
    INSTANCE;

    private static final double TRAINING_PRICE = 3.5;
    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "createTrainingPage";
    private static final String COMMAND_KEY = "command.showTrainings";

    private static final TrainingService TRAINING_SERVICE = TrainingServiceImpl.getInstance();

    private static final ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(CreateTrainingCommand.class);

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
        final String instructorName = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.I_NAME)));

        RESPONSE_CONTEXT.setRedirect(false);

        if (instructorName.equals("") || instructorName.equals("null")) {
            return RESPONSE_CONTEXT;
        }

        createTraining(requestContext, instructorName);
        requestContext.setSessionAttribute(Attributes.BALANCE
                , CLIENT_SERVICE.getClientById((Integer) requestContext.getSessionAttribute(Attributes.ID))
                        .get().getBalance());
        RESPONSE_CONTEXT.setRedirect(true);
        return RESPONSE_CONTEXT;
    }

    private void createTraining(RequestContext requestContext, String instructorName) {
        final int amount = Integer.parseInt(requestContext.getParameter(Attributes.AMOUNT));
        final Integer difficulty = Integer.parseInt(requestContext.getParameter(Attributes.DIFFICULTY));
        final Double price = TRAINING_PRICE * amount;

        try {
            TRAINING_SERVICE.createTraining((Integer) requestContext.getSessionAttribute(Attributes.ID), instructorName
                    , amount, difficulty, price);
        } catch (NoSuchInstructorException | NotEnoughMoneyException e) {
            LOGGER.error(e);
            requestContext.setAttribute(Attributes.ERROR, e.getMessage());
        }
    }
}
