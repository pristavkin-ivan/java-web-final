package com.epam.jwd.fitness_center.command.impl.user;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;
import com.epam.jwd.fitness_center.util.ParamParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ResourceBundle;

public enum SignupCommand implements Command {
    SIGNUP_COMMAND;

    private static final String PAGE_KEY = "signupPage";
    private static final String W_MESSAGE = "\nTry another login.";
    private static final String BUNDLE_NAME = "pages";
    private static final String COMMAND_KEY = "command.default";
    private static final String ADMIN_COMMAND_KEY = "command.showInstructors";
    private static final String ADMIN = "admin";

    private static final Logger LOGGER = LogManager.getLogger(SIGNUP_COMMAND.name());

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

    private static final ResponseContext ADMIN_RESPONSE = new ResponseContext() {

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
        final String login = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.LOGIN)));
        final String password = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.PASSWORD)));
        final String name = ParamParser
                .reduceJsInjection(String.valueOf(requestContext.getParameter(Attributes.NAME)));

        RESPONSE_CONTEXT.setRedirect(false);
        ADMIN_RESPONSE.setRedirect(false);

        if (login.equals(Attributes.NULL) || login.equals("") || password.equals("") || name.equals("")) {
            return RESPONSE_CONTEXT;
        }

        if (ADMIN.equals(requestContext.getSessionAttribute(Attributes.LOGIN))) {
            return addInstructor(requestContext, login, password, name);
        }

        addClient(requestContext, login, password, name);
        return RESPONSE_CONTEXT;
    }

    private void addClient(RequestContext requestContext, String login, String password, String name) {
        try {
            final Optional<ClientDTO> clientDTO = CLIENT_SERVICE.signup(login, name, password);

            clientDTO.ifPresent(dto -> configureSession(requestContext, login, dto));
            RESPONSE_CONTEXT.setRedirect(true);
        } catch (SignupException e) {
            LOGGER.error(e.getMessage());
            requestContext.setAttribute(Attributes.ERROR, e.getMessage() + W_MESSAGE);
        }
    }

    private ResponseContext addInstructor(RequestContext requestContext, String login, String password, String name) {
        try {
            INSTRUCTOR_SERVICE.signup(login, name, password);
            ADMIN_RESPONSE.setRedirect(true);
        } catch (SignupException e) {
            LOGGER.error(e.getMessage());
            requestContext.setAttribute(Attributes.ERROR, e.getMessage() + W_MESSAGE);
        }
        return ADMIN_RESPONSE;
    }

    private void configureSession(RequestContext requestContext, String login, ClientDTO client) {
        requestContext.setSessionAttribute(Attributes.LOGIN, login);
        requestContext.setSessionAttribute(Attributes.ID, client.getId());
        requestContext.setSessionAttribute(Attributes.BALANCE, client.getBalance());
    }

}
