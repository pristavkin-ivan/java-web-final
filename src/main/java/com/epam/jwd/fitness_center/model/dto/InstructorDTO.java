package com.epam.jwd.fitness_center.model.dto;

public class InstructorDTO implements DTO {

    private final Integer id;

    private final String login, name, url, info;

    InstructorDTO(Integer id, String login, String name, String url, String info) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.url = url;
        this.info = info;
    }

    public Integer getId() {
        return id;
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
