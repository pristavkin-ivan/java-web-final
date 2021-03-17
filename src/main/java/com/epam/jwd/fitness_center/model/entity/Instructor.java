package com.epam.jwd.fitness_center.model.entity;

import java.util.Objects;

public class Instructor implements Entity {

    private final Integer id;

    private final String login, name, password, imgUrl, info;

    Instructor(Integer id, String login, String name, String password, String imgUrl, String info) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.imgUrl = imgUrl;
        this.info = info;
    }

    public String getName() {
        return name;
    }

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

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String login, name, password, url, info;

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

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder info(String info) {
            this.info = info;
            return this;
        }

        public Instructor build() {
            return new Instructor(this.id, this.login, this.name, this.password, this.url, this.info);
        }

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

    public String getInfo() {
        return info;
    }

}
