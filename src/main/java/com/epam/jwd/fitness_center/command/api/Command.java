package com.epam.jwd.fitness_center.command.api;

public interface Command {

    ResponseContext execute(RequestContext requestContext);

    static Command of(String name) {
        return CommandManager.of(name);
    }

}
