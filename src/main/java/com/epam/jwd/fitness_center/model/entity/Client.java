package com.epam.jwd.fitness_center.model.entity;

import java.util.List;
import java.util.Objects;

public class Client implements Entity {

    private final Integer id;

    private String login, name, password;

    private Double height, weight;

    private List<Training> trainings;

    Client(Integer id, String login, String name, String password, Double height, Double weight, List<Training> trainings) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.trainings = trainings;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String login, name, password;
        private Double height, weight;
        private List<Training> trainings;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder height(Double height) {
            this.height = height;
            return this;
        }

        public Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder trainings(List<Training> trainings) {
            this.trainings = trainings;
            return this;
        }

        public Client build() {
            return new Client(this.id, this.login, this.name, this.password, this.height, this.weight, this.trainings);
        }

    }

}
