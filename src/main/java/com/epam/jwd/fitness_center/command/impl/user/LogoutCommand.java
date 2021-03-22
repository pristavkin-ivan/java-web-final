package com.epam.jwd.fitness_center.command.impl.user;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.ResourceBundle;

public enum LogoutCommand implements Command {
    LOGOUT_COMMAND;

    private static final String BUNDLE_NAME = "pages";
    private static final String COMMAND_KEY = "command.login";

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return "";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getCommand() {
            return resourceBundle.getString(COMMAND_KEY);
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.invalidateSession();
        return RESPONSE_CONTEXT;
    }

}
