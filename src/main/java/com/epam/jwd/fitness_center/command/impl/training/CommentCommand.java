package com.epam.jwd.fitness_center.command.impl.training;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.TrainingDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.TrainingService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.TrainingServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;


import java.util.ResourceBundle;

public enum CommentCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private static final String COMMAND_KEY = "command.showTrainings";

    private static final TrainingService<TrainingDTO> TRAINING_SERVICE = TrainingServiceImpl.getInstance();

    private static final ClientService<ClientDTO> CLIENT_SERVICE = ClientServiceImpl.getInstance();

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

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String comment = ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.COMMENT));
        final Integer id = Integer.parseInt(requestContext.getParameter(Attributes.TRAINING_ID));

        TRAINING_SERVICE.leaveComment(id, comment);

        RESPONSE_CONTEXT.setRedirect(true);
        return RESPONSE_CONTEXT;
    }
}
