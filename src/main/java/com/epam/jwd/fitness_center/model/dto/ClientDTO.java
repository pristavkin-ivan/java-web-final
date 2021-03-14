package com.epam.jwd.fitness_center.model.dto;

public class ClientDTO implements DTO {

    private final Integer id;

    private final String login, name;

    private final Double height, weight;

    ClientDTO(Integer id, String login, String name, Double height, Double weight) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.height = height;
        this.weight = weight;
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

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }
}
