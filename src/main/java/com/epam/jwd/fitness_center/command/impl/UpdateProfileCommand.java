package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public enum UpdateProfileCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";

    private final static String CLIENT_PAGE_KEY = "clientProfilePage";

    private final static String INSTRUCTOR_PAGE_KEY = "instructorProfilePage";

    private static final ResponseContext CLIENT_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME,
                new Locale("be", "By"));

        @Override
        public String getPage() {
            return resourceBundle.getString(CLIENT_PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    private static final ResponseContext INSTRUCTOR_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME,
                new Locale("be", "By"));

        @Override
        public String getPage() {
            return resourceBundle.getString(INSTRUCTOR_PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        if (Objects.equals(requestContext.getSessionAttribute("isInstructor"), true)) {
            return INSTRUCTOR_RESPONSE_CONTEXT;
        }

        return CLIENT_RESPONSE_CONTEXT;
    }
}
