package com.epam.jwd.fitness_center.command.impl;

import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public enum ShowProfileCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";

    private final static String CLIENT_PAGE_KEY = "clientProfilePage";

    private final static String INSTRUCTOR_PAGE_KEY = "instructorProfilePage";

    private static final ResponseContext CLIENT_RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME,
                new Locale("be", "By"));

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

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME,
                new Locale("be", "By"));

        @Override
        public String getPage() {
            return resourceBundle.getString(INSTRUCTOR_PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    private final static InstructorService INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private final static ClientService CLIENT_SERVICE = ClientServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        if (Objects.equals(requestContext.getSessionAttribute("isInstructor"), true)) {
            final Optional<InstructorDTO> instructor
                    = INSTRUCTOR_SERVICE.getInstructorById((Integer) requestContext.getSessionAttribute("id"));

            configureInstructorProfileAttributes(requestContext, instructor);
            return INSTRUCTOR_RESPONSE_CONTEXT;
        }

        final Optional<ClientDTO> client
                = CLIENT_SERVICE.getClientById((Integer) requestContext.getSessionAttribute("id"));

        configureClientProfileAttributes(requestContext, client);
        return CLIENT_RESPONSE_CONTEXT;
    }

    private void configureInstructorProfileAttributes(RequestContext requestContext, Optional<InstructorDTO> instructor) {
        if (instructor.isPresent()) {
            requestContext.setAttribute("login", instructor.get().getLogin());
            requestContext.setAttribute("name", instructor.get().getName());
            requestContext.setAttribute("info", instructor.get().getInfo());
            requestContext.setAttribute("photoUrl", instructor.get().getUrl());
        }
    }

    private void configureClientProfileAttributes(RequestContext requestContext, Optional<ClientDTO> client ) {
        /*DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);*/

        if (client.isPresent()) {
            requestContext.setAttribute("login", client.get().getLogin());
            requestContext.setAttribute("name", client.get().getName());
            requestContext.setAttribute("height", client.get().getHeight());
            requestContext.setAttribute("weight",client.get().getWeight());
        }
    }

}