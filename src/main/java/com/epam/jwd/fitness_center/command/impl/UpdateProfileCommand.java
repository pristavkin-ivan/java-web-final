package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Attributes;
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
        if (Objects.equals(requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR), true)) {
            updateInstructor(requestContext);
        } else {
            updateClient(requestContext);
        }
        return ShowProfileCommand.INSTANCE.execute(requestContext);
    }

    private void updateClient(RequestContext requestContext) {
        final Client.Builder builder = Client.getBuilder();

        final Client client = builder.id((Integer) requestContext.getSessionAttribute(Attributes.ID))
                .login(requestContext.getParameter(Attributes.LOGIN))
                .name(requestContext.getParameter(Attributes.NAME))
                .height(Double.valueOf(requestContext.getParameter(Attributes.HEIGHT)))
                .weight(Double.valueOf(requestContext.getParameter(Attributes.WEIGHT)))
                .build();

        CLIENT_SERVICE.updateProfile(client);
    }

    private void updateInstructor(RequestContext requestContext) {
        final Instructor.Builder builder = Instructor.getBuilder();

        final Instructor instructor = builder.id((Integer) requestContext.getSessionAttribute(Attributes.ID))
                .login(requestContext.getParameter(Attributes.LOGIN))
                .name(requestContext.getParameter(Attributes.NAME))
                .url(requestContext.getParameter(Attributes.PHOTO_URL))
                .info(requestContext.getParameter(Attributes.INFO))
                .build();

        INSTRUCTOR_SERVICE.updateProfile(instructor);
    }

}
