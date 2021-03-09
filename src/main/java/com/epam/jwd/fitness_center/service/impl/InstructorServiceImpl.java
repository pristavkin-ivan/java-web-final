package com.epam.jwd.fitness_center.service.impl;

import com.epam.jwd.fitness_center.service.api.InstructorService;

public final class InstructorServiceImpl implements InstructorService {

    private static final InstructorServiceImpl INSTRUCTOR_SERVICE = new InstructorServiceImpl();

    private InstructorServiceImpl() {
    }

    public static InstructorServiceImpl getInstance() {
        return INSTRUCTOR_SERVICE;
    }


}
