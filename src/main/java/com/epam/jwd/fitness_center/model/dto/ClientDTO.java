package com.epam.jwd.fitness_center.model.dto;

public class ClientDTO implements DTO {

    private final String login, name;

    ClientDTO(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
