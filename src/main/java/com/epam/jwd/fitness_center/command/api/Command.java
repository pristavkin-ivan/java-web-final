package com.epam.jwd.fitness_center.command.api;

/**
 * Interface that processing and parsing user's requests, calls the appropriate business logic and
 * send the response to user.
 */
public interface Command {

    /**
     * This method processing and parsing user's requests, calls the appropriate business logic and
     * send the response to user
     *
     * @param requestContext
     * @return appropriate response context
     */
    ResponseContext execute(RequestContext requestContext);

    /**
     * Factory Method that get appropriate Command implementation by Command's name
     *
     * @param name
     * @return Command implementation
     */
    static Command of(String name) {
        return CommandManager.of(name);
    }

}
