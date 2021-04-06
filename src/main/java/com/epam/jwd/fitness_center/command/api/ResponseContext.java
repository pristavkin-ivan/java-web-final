package com.epam.jwd.fitness_center.command.api;

import javax.servlet.http.Cookie;

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

    /**
     * Retrieves cookie from response context
     *
     * @return Cookie
     */
    default Cookie getCookie() {
        return null;
    }

    /**
     * Set cookie in response context
     * @param cookie
     */
    default void setCookie(Cookie cookie){}

}
