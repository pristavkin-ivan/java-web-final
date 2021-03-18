package com.epam.jwd.fitness_center.model.dto;

import com.epam.jwd.fitness_center.model.entity.Purposes;

public class TrainingDTO implements DTO {

    private final String instructorName, instructorPhotoUrl, clientName;

    private final Integer id, amount, difficulty;

    private final Double price;

    private String comment = "Comment";

    private final Purposes purposes;

    TrainingDTO(Integer id, String instructorName, String clientName, String instructorPhotoUrl, Integer amount
            , Integer difficulty, Purposes purposes, Double price) {
        this.id = id;
        this.instructorName = instructorName;
        this.clientName = clientName;
        this.instructorPhotoUrl = instructorPhotoUrl;
        this.amount = amount;
        this.difficulty = difficulty;
        this.purposes = purposes;
        this.price = price;
    }

    public String getClientName() {
        return clientName;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorPhotoUrl() {
        return instructorPhotoUrl;
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

    @Override
    public String toString() {
        return "TrainingDTO{" +
                "instructorName='" + instructorName + '\'' +
                ", instructorPhotoUrl='" + instructorPhotoUrl + '\'' +
                ", amount=" + amount +
                ", difficulty=" + difficulty +
                ", purposes=" + purposes +
                '}';
    }
}
