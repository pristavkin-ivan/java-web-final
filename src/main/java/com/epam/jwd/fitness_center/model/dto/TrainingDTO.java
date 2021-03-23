package com.epam.jwd.fitness_center.model.dto;

import com.epam.jwd.fitness_center.model.entity.Purpose;

import java.util.List;

public class TrainingDTO implements DTO {

    private final String instructorName, instructorPhotoUrl, clientName, comment;

    private final Integer id, amount, difficulty;

    private final Double price;

    private final List<Purpose> purposes;

    TrainingDTO(Integer id, String instructorName, String clientName, String instructorPhotoUrl, Integer amount
            , Integer difficulty, List<Purpose> purposes, Double price, String comment) {
        this.id = id;
        this.instructorName = instructorName;
        this.clientName = clientName;
        this.instructorPhotoUrl = instructorPhotoUrl;
        this.amount = amount;
        this.difficulty = difficulty;
        this.purposes = purposes;
        this.price = price;
        this.comment = comment;
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

    public List<Purpose> getPurposes() {
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
