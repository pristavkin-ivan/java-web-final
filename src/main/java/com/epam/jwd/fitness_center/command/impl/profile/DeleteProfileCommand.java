package com.epam.jwd.fitness_center.command.impl.profile;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.command.impl.user.LogoutCommand;
import com.epam.jwd.fitness_center.model.dto.ClientDTO;
import com.epam.jwd.fitness_center.model.dto.InstructorDTO;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.ResourceBundle;

public enum DeleteProfileCommand implements Command {
    INSTANCE;

    private static final ClientService<ClientDTO> CLIENT_SERVICE = ClientServiceImpl.getInstance();

    private static final InstructorService<InstructorDTO> INSTRUCTOR_SERVICE = InstructorServiceImpl.getInstance();

    private final static String BUNDLE_NAME = "pages";
    private final static String COMMAND_KEY = "command.showInstructors";

    private static final ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        @Override
        public String getPage() {
            return null;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getCommand() {
            return resourceBundle.getString(COMMAND_KEY);
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        Integer id;

        if (requestContext.getParameter(Attributes.DELETE_ID) != null) {
            id = Integer.parseInt(requestContext.getParameter(Attributes.DELETE_ID));
        } else {
            id = (Integer) requestContext.getSessionAttribute(Attributes.ID);
        }

        if (requestContext.getSessionAttribute(Attributes.IS_INSTRUCTOR) != null) {
            INSTRUCTOR_SERVICE.deleteProfile(id);
            return RESPONSE_CONTEXT;
        } else {
            CLIENT_SERVICE.deleteProfile(id);
        }

        return LogoutCommand.LOGOUT_COMMAND.execute(requestContext);
    }
}
