package com.epam.jwd.fitness_center.util;

public class ParamParser {

    public static String reduceJsInjection(String param) {
        param = param.replaceAll(">", "");
        param = param.replaceAll("<", "");
        return param;
    }
}
