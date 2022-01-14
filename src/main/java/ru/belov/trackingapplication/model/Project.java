package ru.belov.trackingapplication.model;

import lombok.Data;

@Data
public class Project {

    private int id;
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project() {
    }

}
