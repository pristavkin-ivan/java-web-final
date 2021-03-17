package com.epam.jwd.fitness_center.model.dto;

public class ClientDTO implements DTO {

    private final Integer id;

    private final String login, name;

    private final Double height, weight, balance;


    ClientDTO(Integer id, String login, String name, Double height, Double weight, Double balance) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
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
