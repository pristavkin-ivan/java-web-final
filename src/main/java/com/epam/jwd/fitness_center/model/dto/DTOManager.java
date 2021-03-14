package com.epam.jwd.fitness_center.model.dto;

public enum DTOManager {
    DTO_MANAGER;

    public ClientDTO createClientDTO(Integer id, String login, String name, Double height, Double weight) {
        return new ClientDTO(id, login, name, height, weight);
    }

    public InstructorDTO createInstructorDTO(Integer id, String login, String name, String url, String info) {
        return new InstructorDTO(id, login, name, url, info);
    }

}
