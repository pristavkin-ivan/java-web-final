package com.epam.jwd.fitness_center.model.entity;

public final class Training implements Entity {

    private final Integer id;

    private final String name;

    public Training(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public Integer getId() {
        return null;
    }
}
