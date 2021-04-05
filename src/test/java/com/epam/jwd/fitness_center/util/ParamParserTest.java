package com.epam.jwd.fitness_center.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamParserTest {

    private final static String INPUT = "asd <54te<<89><>r";
    private final static String OUTPUT = "asd 54te89r";

    @Test
    public void ReduceJsInjectionText_MustReduceAllTagsFromString_String() {
        assertEquals(ParamParser.reduceJsInjection(INPUT), OUTPUT);
    }

}
