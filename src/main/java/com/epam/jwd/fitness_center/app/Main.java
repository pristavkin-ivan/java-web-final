package com.epam.jwd.fitness_center.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("hey!");
        logger.error("hey!");
    }
}
