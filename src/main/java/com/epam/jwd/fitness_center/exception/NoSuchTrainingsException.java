package com.epam.jwd.fitness_center.exception;

public class NoSuchTrainingsException extends Exception {

    public NoSuchTrainingsException() {
        super();
    }

    public NoSuchTrainingsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
