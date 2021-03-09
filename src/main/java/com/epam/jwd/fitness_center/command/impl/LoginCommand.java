package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LoginCommand implements Command {
    LOGIN_COMMAND;

    private static final String PAGE_KEY = "loginPage";

    private static final String BUNDLE_NAME = "pages";

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME,
                new Locale("be", "By"));

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
        final String login = String.valueOf(requestContext.getParameter("login"));
        final String password = String.valueOf(requestContext.getParameter("password"));

        if (login.equals("null") || login.equals("") || password.equals("")) {
            return RESPONSE_CONTEXT;
        }
        final ClientService service = ClientServiceImpl.getInstance();

        if (service.login(login, password).isPresent()) {
            requestContext.setSessionAttribute("login", login);
            return DefaultCommand.DEFAULT_COMMAND.execute(requestContext);
        }
        requestContext.setAttribute("error", "Incorrect login or password, try again!");
        return RESPONSE_CONTEXT;
    }
}
