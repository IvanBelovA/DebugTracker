package ru.belov.trackingapplication.model;

import lombok.Data;

@Data
public class Task {

    private int id;
    private Project project;
    private Topic topic;
    private Type type;
    private User user;
    private Priority priority;
    private String description;

    public Task(int id, Project project, Topic topic, Type type, User user, Priority priority, String description) {
        this.id = id;
        this.project = project;
        this.topic = topic;
        this.type = type;
        this.user = user;
        this.priority = priority;
        this.description = description;
    }

}
