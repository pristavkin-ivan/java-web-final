package com.epam.jwd.fitness_center.model.entity;

import java.util.Objects;

public class Client implements Entity {

    private final Integer id;

    private String login, name, password;

    public Client(Integer id, String login, String name, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Client(String login, String name, String password) {
        this.id = null;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(login, client.login)
                && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
