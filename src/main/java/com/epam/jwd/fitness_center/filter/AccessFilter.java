package com.epam.jwd.fitness_center.filter;

import com.epam.jwd.fitness_center.command.api.Attributes;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

@WebFilter(filterName = "accessFilter", urlPatterns = "/go")
public class AccessFilter implements Filter {

    private static final String COMMANDS_BUNDLE_NAME = "instructorCommands";

    private static final String PAGES_BUNDLE_NAME = "pages";

    private static final String DEFAULT_COMMAND_URI = "command.default";

    private static final ResourceBundle COMMANDS_BUNDLE = ResourceBundle.getBundle(COMMANDS_BUNDLE_NAME);

    private static final ResourceBundle PAGES_BUNDLE = ResourceBundle.getBundle(PAGES_BUNDLE_NAME);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final Set<String> keySet = COMMANDS_BUNDLE.keySet();

        checkCommand(servletRequest, servletResponse, filterChain, keySet);
    }

    private void checkCommand(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
            , Set<String> keySet) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final String command = String.valueOf(httpServletRequest.getParameter(Attributes.COMMAND));
        boolean flag = true;

        if (httpServletRequest.getSession().getAttribute(Attributes.IS_INSTRUCTOR) == null) {
            for (String key : keySet) {
                if (COMMANDS_BUNDLE.getString(key).equalsIgnoreCase(command)) {
                    flag = false;
                }
            }
        }

        process(servletRequest, servletResponse, filterChain, flag);
    }

    private void process(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain
            , boolean flag) throws IOException, ServletException {
        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(PAGES_BUNDLE.getString(DEFAULT_COMMAND_URI));
        }
    }

}
