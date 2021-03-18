package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.exception.SignupException;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public enum SignupCommand implements Command {
    SIGNUP_COMMAND;

    private static final String PAGE_KEY = "signupPage";
    private static final String W_MESSAGE = "\nTry another login.";
    private static final String BUNDLE_NAME = "pages";

    private static final Logger LOGGER = LogManager.getLogger(SIGNUP_COMMAND.name());

    private static final ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

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
        final String login = String.valueOf(requestContext.getParameter(Attributes.LOGIN));
        final String password = String.valueOf(requestContext.getParameter(Attributes.PASSWORD));
        final String name = String.valueOf(requestContext.getParameter(Attributes.NAME));

        if (login.equals(Attributes.NULL) || login.equals("") || password.equals("") || name.equals("")) {
            return RESPONSE_CONTEXT;
        }

        try {
            CLIENT_SERVICE.signup(login, name, password);
            requestContext.setSessionAttribute(Attributes.LOGIN, login);
            return DefaultCommand.DEFAULT_COMMAND.execute(requestContext);
        } catch (SignupException e) {
            LOGGER.error(e.getMessage());
            requestContext.setAttribute(Attributes.ERROR, e.getMessage() + W_MESSAGE);
        }
        return RESPONSE_CONTEXT;
    }

}
