package com.epam.jwd.fitness_center.tag;

import com.epam.jwd.fitness_center.command.api.Attributes;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class LoginTag extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {
        String login = (String) pageContext.getSession().getAttribute(Attributes.LOGIN);
        final JspWriter writer = pageContext.getOut();

        try {
            writer.write(login);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
