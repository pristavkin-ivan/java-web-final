package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;

public enum LogoutCommand implements Command {
    LOGOUT_COMMAND;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.invalidateSession();
        return LoginCommand.LOGIN_COMMAND.execute(requestContext);
    }
}
