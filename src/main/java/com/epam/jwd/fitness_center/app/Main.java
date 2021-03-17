package com.epam.jwd.fitness_center.app;

import com.epam.jwd.fitness_center.command.api.ResponseContext;
import com.epam.jwd.fitness_center.service.api.ClientService;
import com.epam.jwd.fitness_center.service.api.InstructorService;
import com.epam.jwd.fitness_center.service.impl.ClientServiceImpl;
import com.epam.jwd.fitness_center.service.impl.InstructorServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    private static final String BUNDLE_NAME = "pagecontent_ru_RU";

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        System.out.println(resourceBundle.getString("hey"));

    }
}
