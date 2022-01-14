package ru.belov.trackingapplication.dto;

import lombok.Data;

@Data
public class TaskDto {

    private int id;
    private int projectId;
    private int topicId;
    private int typeId;
    private int userId;
    private int priorityId;
    private String description;

}
