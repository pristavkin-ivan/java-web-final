package com.epam.jwd.fitness_center.command.impl.user;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;

import java.util.Optional;
import java.util.ResourceBundle;

public enum LoginCommand implements Command {
    LOGIN_COMMAND;

    private static final String PAGE_KEY = "loginPage";
    private static final String BUNDLE_NAME = "pages";
    private static final String ERROR_MESSAGE = "Incorrect login or password, try again!";
    private static final String COMMAND_KEY = "command.default";

    private static final ClientService<ClientDTO> CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private static final InstructorService<InstructorDTO> INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

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
        final String login = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.LOGIN)));
        final String password = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.PASSWORD)));

        RESPONSE_CONTEXT.setRedirect(false);

        if (login.equals(Attributes.NULL) || login.equals("") || password.equals("")) {
            return RESPONSE_CONTEXT;
        }

        return login(requestContext, login, password);
    }

    private ResponseContext login(RequestContext requestContext, String login, String password) {
        if (!Boolean.parseBoolean(requestContext.getParameter(Attributes.IS_INSTRUCTOR))) {

            if (loginClient(requestContext, login, password)) {
                RESPONSE_CONTEXT.setRedirect(true);
                return RESPONSE_CONTEXT;
            }
        } else {

            if (loginInstructor(requestContext, login, password)) {
                RESPONSE_CONTEXT.setRedirect(true);
                return RESPONSE_CONTEXT;
            }
        }

        requestContext.setAttribute(Attributes.ERROR, ERROR_MESSAGE);
        return RESPONSE_CONTEXT;
    }

    private boolean loginClient(RequestContext requestContext, String login, String password) {
        final Optional<ClientDTO> client = CLIENT_SERVICE.login(login, password);

        if (client.isPresent()) {
            requestContext.setSessionAttribute(Attributes.LOGIN, login);
            requestContext.setSessionAttribute(Attributes.ID, client.get().getId());
            requestContext.setSessionAttribute(Attributes.BALANCE, client.get().getBalance());
            return true;
        }
        return false;
    }

    private boolean loginInstructor(RequestContext requestContext, String login, String password) {
        final Optional<InstructorDTO> instructor = INSTRUCTOR_SERVICE.login(login, password);

        if (instructor.isPresent()) {
            requestContext.setSessionAttribute(Attributes.IS_INSTRUCTOR, true);
            requestContext.setSessionAttribute(Attributes.LOGIN, login);
            requestContext.setSessionAttribute(Attributes.ID, instructor.get().getId());
            return true;
        }
        return false;
    }

}
