package com.epam.jwd.fitness_center.command.impl.user;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ShowInstructorsCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "instructorsPage";

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

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final InstructorService service = InstructorServiceImpl.getInstance();

        requestContext.setAttribute(Attributes.INSTRUCTORS, service.getAllInstructors());
        return RESPONSE_CONTEXT;
    }
}
