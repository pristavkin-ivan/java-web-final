package com.epam.jwd.fitness_center.command.impl.profile;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public enum ShowProfileCommand implements Command {
    INSTANCE;

    private final static InstructorService<InstructorDTO> INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private final static ClientService<ClientDTO> CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private final static String BUNDLE_NAME = "pages";
    private final static String CLIENT_PAGE_KEY = "clientProfilePage";
    private final static String INSTRUCTOR_PAGE_KEY = "instructorProfilePage";

    private static final ResponseContext CLIENT_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return resourceBundle.getString(CLIENT_PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    private static final ResponseContext INSTRUCTOR_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return resourceBundle.getString(INSTRUCTOR_PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        if (Objects.equals(requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR), true)) {
            final Optional<InstructorDTO> instructor
                    = INSTRUCTOR_SERVICE.getInstructorById((Integer) requestContext.getSessionAttribute(Attributes.ID));

            configureInstructorProfileAttributes(requestContext, instructor);
            return INSTRUCTOR_RESPONSE_CONTEXT;
        }

        final Optional<ClientDTO> client
                = CLIENT_SERVICE.getClientById((Integer) requestContext.getSessionAttribute(Attributes.ID));

        configureClientProfileAttributes(requestContext, client);
        return CLIENT_RESPONSE_CONTEXT;
    }

    private void configureInstructorProfileAttributes(RequestContext requestContext, Optional<InstructorDTO> instructor) {
        if (instructor.isPresent()) {
            requestContext.setAttribute(Attributes.LOGIN, instructor.get().getLogin());
            requestContext.setAttribute(Attributes.NAME, instructor.get().getName());
            requestContext.setAttribute(Attributes.INFO, instructor.get().getInfo());
            requestContext.setAttribute(Attributes.PHOTO_URL, instructor.get().getUrl());
        }
    }

    private void configureClientProfileAttributes(RequestContext requestContext, Optional<ClientDTO> client ) {
        if (client.isPresent()) {
            requestContext.setAttribute(Attributes.LOGIN, client.get().getLogin());
            requestContext.setAttribute(Attributes.NAME, client.get().getName());
            requestContext.setAttribute(Attributes.HEIGHT, client.get().getHeight());
            requestContext.setAttribute(Attributes.WEIGHT,client.get().getWeight());
        }
    }

}
