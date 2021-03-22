package com.epam.jwd.fitness_center.command.impl.profile;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;

import java.util.Objects;
import java.util.ResourceBundle;

public enum UpdateProfileCommand implements Command {
    INSTANCE;

    private final static InstructorService INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private final static ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private static final String BUNDLE_NAME = "pages";
    private static final String COMMAND_KEY = "command.showProfile";

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        private boolean redirect = true;

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
        if (Objects.equals(requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR), true)) {
            updateInstructor(requestContext);
        } else {
            updateClient(requestContext);
        }
        return RESPONSE_CONTEXT;
    }

    private void updateClient(RequestContext requestContext) {
        final Client.Builder builder = Client.getBuilder();

        final Client client = builder.id((Integer) requestContext.getSessionAttribute(Attributes.ID))
                .login(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.LOGIN)))
                .name(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.NAME)))
                .height(Double.valueOf(requestContext.getParameter(Attributes.HEIGHT)))
                .weight(Double.valueOf(requestContext.getParameter(Attributes.WEIGHT)))
                .build();

        CLIENT_SERVICE.updateProfile(client);
    }

    private void updateInstructor(RequestContext requestContext) {
        final Instructor.Builder builder = Instructor.getBuilder();

        final Instructor instructor = builder.id((Integer) requestContext.getSessionAttribute(Attributes.ID))
                .login(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.LOGIN)))
                .name(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.NAME)))
                .url(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.PHOTO_URL)))
                .info(ParamParser.reduceJsInjection(requestContext.getParameter(Attributes.INFO)))
                .build();

        INSTRUCTOR_SERVICE.updateProfile(instructor);
    }

}
