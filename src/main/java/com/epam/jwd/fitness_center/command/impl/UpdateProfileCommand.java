package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.entity.Client;
import com.epam.jwd.fitness_center.model.entity.Instructor;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.Objects;

public enum UpdateProfileCommand implements Command {
    INSTANCE;

    private final static InstructorService INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private final static ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        if (Objects.equals(requestContext.getSessionAttribute("isInstructor"), true)) {
            updateInstructor(requestContext);
        } else {
            updateClient(requestContext);
        }
        return ShowProfileCommand.INSTANCE.execute(requestContext);
    }

    private void updateClient(RequestContext requestContext) {
        final Client.Builder builder = Client.getBuilder();

        final Client client = builder.id((Integer) requestContext.getSessionAttribute("id"))
                .login(requestContext.getParameter("login"))
                .name(requestContext.getParameter("name"))
                .height(Double.valueOf(requestContext.getParameter("height")))
                .weight(Double.valueOf(requestContext.getParameter("weight")))
                .build();

        CLIENT_SERVICE.updateProfile(client);
    }

    private void updateInstructor(RequestContext requestContext) {
        final Instructor.Builder builder = Instructor.getBuilder();

        final Instructor instructor = builder.id((Integer) requestContext.getSessionAttribute("id"))
                .login(requestContext.getParameter("login"))
                .name(requestContext.getParameter("name"))
                .url(requestContext.getParameter("photoUrl"))
                .info(requestContext.getParameter("info"))
                .build();

        INSTRUCTOR_SERVICE.updateProfile(instructor);
    }

}
