package com.epam.jwd.fitness_center.controller;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.command.impl.RequestContextImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/go")
public class Controller extends HttpServlet {

    private final static Logger CONTROLLER_LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        command(req, resp);
    }

    private void command(HttpServletRequest req, HttpServletResponse resp) {
        final String name = req.getParameter("command");
        final Command command = Command.of(name);
        final ResponseContext response = command.execute(new RequestContextImpl(req));

        if (response.isRedirect()) {
            // todo redirect
        } else {
            try {
                req.getRequestDispatcher(response.getPage()).forward(req, resp);
            } catch (ServletException | IOException e) {
                CONTROLLER_LOGGER.error("Page can't be forwarded!", e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void destroy() {
    }
}