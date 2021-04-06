package com.epam.jwd.fitness_center.command.impl.common;

import com.epam.jwd.fitness_center.command.api.Attributes;
import com.epam.jwd.fitness_center.command.api.Command;
import com.epam.jwd.fitness_center.command.api.RequestContext;
import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.CaloriesService;
import com.epam.jwd.fitness_center.service.impl.calories.GainWeightCaloriesService;
import com.epam.jwd.fitness_center.service.impl.calories.LossWeightCaloriesService;
import com.epam.jwd.fitness_center.service.impl.calories.NormalWeightCaloriesService;

import java.util.ResourceBundle;

public enum CalculateCaloriesCommand implements Command {
    INSTANCE;

    private final static String BUNDLE_NAME = "pages";
    private final static String PAGE_KEY = "calculatorPage";

    private CaloriesService caloriesService;

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
        final String height = requestContext.getParameter(Attributes.HEIGHT);
        final String weight = requestContext.getParameter(Attributes.WEIGHT);


        if (height == null || weight == null) {
            return RESPONSE_CONTEXT;
        }

        if (String.valueOf(requestContext.getParameter(Attributes.GENDER)).equalsIgnoreCase(Attributes.FEMALE)) {
            calculateFemaleCalories(requestContext, height, weight);
        } else {
            calculateMaleCalories(requestContext, height, weight);
        }

        return RESPONSE_CONTEXT;
    }

    private void calculateMaleCalories(RequestContext requestContext, String height, String weight) {
        caloriesService = NormalWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.NORMAL
                , caloriesService.calculateMaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
        caloriesService = LossWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.LOSS
                , caloriesService.calculateMaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
        caloriesService = GainWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.GAIN
                , caloriesService.calculateMaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
    }

    private void calculateFemaleCalories(RequestContext requestContext, String height, String weight) {
        caloriesService = NormalWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.NORMAL
                , caloriesService.calculateFemaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
        caloriesService = LossWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.LOSS
                , caloriesService.calculateFemaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
        caloriesService = GainWeightCaloriesService.INSTANCE;
        requestContext.setAttribute(Attributes.GAIN
                , caloriesService.calculateFemaleCalories(Double.parseDouble(height), Double.parseDouble(weight)));
    }
}
