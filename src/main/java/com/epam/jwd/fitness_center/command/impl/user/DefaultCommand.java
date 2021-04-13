package com.epam.jwd.fitness_center.command.impl.user;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.command.impl.training.CreateTrainingCommand;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.ResourceBundle;

public enum DefaultCommand implements Command {
    DEFAULT_COMMAND;

    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "mainPage";

    private final static InstructorService<InstructorDTO> INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return resourceBundle.getString(PAGE_KEY);
        }

        @Override
        public boolean isRedirect() {
            return false;
        }

    };

    private static final int ADMIN_ID = 6;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        if (requestContext.getSessionAttribute(Attributes.LOGIN) == null) {
            return LoginCommand.LOGIN_COMMAND.execute(requestContext);
        }
        CreateTrainingCommand.INSTANCE.setBalance(requestContext);

        final InstructorDTO adminDTO = INSTRUCTOR_SERVICE.findInstructorById(ADMIN_ID).get();

        requestContext.setAttribute(Attributes.INFO, adminDTO.getInfo());
        return RESPONSE_CONTEXT;
    }

}
