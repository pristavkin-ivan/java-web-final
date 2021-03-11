package com.epam.jwd.fitness_center.model.dto;

public enum DTOManager {
    DTO_MANAGER;

    public ClientDTO createClientDTO(String login, String name) {
        return new ClientDTO(login, name);
    }

    public InstructorDTO createInstructorDTO(String login, String name, String url) {
        return new InstructorDTO(login, name, url);
    }

}
