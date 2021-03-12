package com.epam.jwd.fitness_center.model.dto;

public class InstructorDTO implements DTO {

    private final String login, name, url, info;

    InstructorDTO(String login, String name, String url, String info) {
        this.login = login;
        this.name = name;
        this.url = url;
        this.info = info;
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

    public String getInfo() {
        return info;
    }
}
