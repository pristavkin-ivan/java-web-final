package com.epam.jwd.fitness_center.model.entity;

import java.util.List;

public final class Training implements Entity {

    private Integer id, amount, difficulty;

    private Double price;

    private Purposes purposes;

    private Client client;

    private Instructor instructor;

    Training(Integer id, Client client, Instructor instructor, Integer amount, Integer difficulty
            , Purposes purposes, Double price) {
        this.id = id;
        this.client = client;
        this.instructor = instructor;
        this.amount = amount;
        this.difficulty = difficulty;
        this.purposes = purposes;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public Purposes getPurposes() {
        return purposes;
    }

    public Client getClient() {
        return client;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", amount=" + amount +
                ", difficulty=" + difficulty +
                ", purposes=" + purposes +
                ", client=" + client +
                ", instructor=" + instructor +
                '}';
    }

    public static class Builder {
        private Integer id, amount, difficulty;

        private Double price;

        private Purposes purposes;

        private Client client;

        private Instructor instructor;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder difficulty(Integer difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder purposes(Purposes purposes) {
            this.purposes = purposes;
            return this;
        }

        public Integer getId() {
            return id;
        }

        public Training build() {
            return new Training(this.id, this.client, this.instructor, this.amount, this.difficulty
                    , this.purposes, this.price);
        }

    }
}
