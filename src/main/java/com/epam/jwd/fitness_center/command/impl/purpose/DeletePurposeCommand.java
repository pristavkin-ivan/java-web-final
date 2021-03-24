package com.epam.jwd.fitness_center.command.impl.purpose;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.command.impl.training.InspectTrainingCommand;
import com.epam.jwd.fitness_center.service.api.PurposeService;
import com.epam.jwd.fitness_center.service.impl.PurposeServiceImpl;


public enum DeletePurposeCommand implements Command {
    INSTANCE;

    private final static PurposeService PURPOSE_SERVICE = PurposeServiceImpl.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final Integer purposeId = Integer.parseInt(requestContext.getParameter(Attributes.PURPOSE_ID));

        PURPOSE_SERVICE.deletePurpose(purposeId);
        return InspectTrainingCommand.INSTANCE.execute(requestContext);
    }
}
