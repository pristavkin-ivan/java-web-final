package com.epam.jwd.fitness_center.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instructor implements Entity {

    private final Integer id;

    private String login, name, password, imgUrl;

    private List<Training> trainings;

    Instructor(Integer id, String login, String name, String password, List<Training> trainingList
            , String imgUrl) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.trainings = trainingList;
        this.imgUrl = imgUrl;
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
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(name, that.name)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTraining(Training training) {
        if (trainings == null) {
            trainings = new ArrayList<>();
        }
        trainings.add(training);
    }

    public List<Training> getTrainings() {
        return trainings;
    }


}
