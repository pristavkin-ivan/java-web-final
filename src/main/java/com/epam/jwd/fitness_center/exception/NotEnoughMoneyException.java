package com.epam.jwd.fitness_center.exception;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
        super();
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
