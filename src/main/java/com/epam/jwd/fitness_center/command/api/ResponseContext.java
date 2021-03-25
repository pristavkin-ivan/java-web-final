package com.epam.jwd.fitness_center.command.api;

/**
 * Interface of response's context.
 */
public interface ResponseContext {

    /**
     * Retrieves page uri
     *
     * @return page uri
     */
    String getPage();

    /**
     * Check if page should be redirect
     *
     * @return true if should be redirect. Otherwise - false
     */
    boolean isRedirect();

    /**
     * Set redirect parameter
     *
     * @param redirect
     */
    default void setRedirect(boolean redirect) {}

    /**
     * Retrieves command uri
     *
     * @return command uri
     */
    default String getCommand() {
        return "";
    }

}
