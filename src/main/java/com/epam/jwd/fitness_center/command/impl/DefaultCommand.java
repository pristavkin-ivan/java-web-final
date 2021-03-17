package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;

import java.util.Locale;
import java.util.ResourceBundle;

public enum DefaultCommand implements Command {
    DEFAULT_COMMAND;

    private final static String BUNDLE_NAME = "pages";

    private final static String PAGE_KEY = "mainPage";

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
        String localization = requestContext.getParameter("localization");

        if (requestContext.getSessionAttribute("login") == null) {
            if (localization == null) {
                localization = "en";
            }

            requestContext.setSessionAttribute("localization", localization);
            return LoginCommand.LOGIN_COMMAND.execute(requestContext);
        }

        if (localization != null) {
            requestContext.setSessionAttribute("localization", localization);
        }
        return RESPONSE_CONTEXT;
    }

}
