package com.epam.jwd.fitness_center.command.impl.training;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import com.epam.jwd.fitness_center.service.impl.TrainingServiceImpl;

import java.util.ResourceBundle;

public enum DeleteTrainingCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String COMMAND_KEY = "command.showTrainings";
    private final static String ADMIN_COMMAND_KEY = "command.showAllTrainings";

    private final static TrainingService TRAINING_SERVICE = TrainingServiceImpl.getInstance();

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        private boolean redirect = false;

        @Override
        public String getPage() {
            return "";
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

    private static final ResponseContext ADMIN_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        private boolean redirect = false;

        @Override
        public String getPage() {
            return "";
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
            return resourceBundle.getString(ADMIN_COMMAND_KEY);
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        TRAINING_SERVICE.deleteTraining(Integer.parseInt(requestContext.getParameter(Attributes.DELETE_ID)));

        if (requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR) != null
                && Attributes.ADMIN.equals(requestContext.getSessionAttribute(Attributes.LOGIN))) {
            ADMIN_RESPONSE_CONTEXT.setRedirect(true);
            return ADMIN_RESPONSE_CONTEXT;
        }

        RESPONSE_CONTEXT.setRedirect(true);
        return RESPONSE_CONTEXT;
    }
}
