package com.epam.jwd.fitness_center.command.api;

public interface ResponseContext {

    String getPage();

    boolean isRedirect();

    default void setRedirect(boolean redirect) {}

    default String getCommand() {
        return "";
    }

}
