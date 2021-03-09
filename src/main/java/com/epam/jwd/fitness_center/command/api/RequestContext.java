package com.epam.jwd.fitness_center.command.api;

public interface RequestContext {

    void setAttribute(String attribute, Object value);

    void invalidateSession();

    void setSessionAttribute(String attribute, Object value);

    Object getAttribute(String name);

    String getParameter(String name);

}
