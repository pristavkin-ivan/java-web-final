package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestContextImpl implements RequestContext {

    private final HttpServletRequest request;

    public RequestContextImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String attribute, Object value) {
        request.setAttribute(attribute, value);
    }

    @Override
    public void invalidateSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void setSessionAttribute(String attribute, Object value) {
        request.getSession().setAttribute(attribute, value);
    }

    @Override
    public Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
}
