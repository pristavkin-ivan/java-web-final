package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorCommand implements Command {
    ERROR_COMMAND;

    private final static String BUNDLE_NAME = "pages";

    private final static String PAGE_KEY = "errorPage";

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
        return RESPONSE_CONTEXT;
    }

}
