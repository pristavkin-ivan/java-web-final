package com.epam.jwd.fitness_center.model.dto;

public class InstructorDTO implements DTO {

    private final String login, name, url;

    InstructorDTO(String login, String name, String url) {
        this.login = login;
        this.name = name;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
