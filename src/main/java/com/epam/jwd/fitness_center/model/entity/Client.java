package com.epam.jwd.fitness_center.model.entity;

import java.util.Objects;

public class Client implements Entity {

    private final Integer id;

    private final String login, name, password;

    private final Double height, weight, balance;

    Client(Integer id, String login, String name, String password, Double height, Double weight, Double balance) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
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

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String login, name, password;
        private Double height, weight, balance;

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

        public Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Client build() {
            return new Client(this.id, this.login, this.name, this.password, this.height, this.weight, this.balance);
        }

    }

}
