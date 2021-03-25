package com.epam.jwd.fitness_center.command.api;

/**
 * Interface of request's context.
 */
public interface RequestContext {

    /**
     * Set request's attribute
     *
     * @param attribute
     * @param value
     */
    void setAttribute(String attribute, Object value);

    /**
     * Invalidates session
     */
    void invalidateSession();

    /**
     * Set session's attribute
     *
     * @param attribute
     * @param value
     */
    void setSessionAttribute(String attribute, Object value);

    /**
     * Retrieves session's attribute
     *
     * @param name
     * @return session's attribute
     */
    Object getSessionAttribute(String name);

    /**
     * Retrieves request's attribute
     *
     * @param name
     * @return request's attribute
     */
    Object getAttribute(String name);

    /**
     * Retrieves request's parameter by name
     *
     * @param name
     * @return request's parameter
     */
    String getParameter(String name);

}
