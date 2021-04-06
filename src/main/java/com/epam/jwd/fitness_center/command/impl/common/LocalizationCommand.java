package com.epam.jwd.fitness_center.command.impl.common;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;

import javax.servlet.http.Cookie;
import java.util.ResourceBundle;

public enum LocalizationCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String LOCALE = "locale";
    private static final String COMMAND_KEY = "command.default";

    private final static ResponseContext RESPONSE_CONTEXT = new ResponseContext() {

        private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

        private Cookie locale;

        @Override
        public String getPage() {
            return "";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public Cookie getCookie() {
            return locale;
        }

        @Override
        public String getCommand() {
            return resourceBundle.getString(COMMAND_KEY);
        }

        @Override
        public void setCookie(Cookie locale) {
            this.locale = locale;
        }

    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String parameter = requestContext.getParameter(Attributes.LOCALIZATION);
        final Cookie locale = new Cookie(LOCALE, parameter);

        RESPONSE_CONTEXT.setCookie(locale);
        return RESPONSE_CONTEXT;
    }

}
