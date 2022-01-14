package ru.belov.trackingapplication.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private String lastName;
    private String speciality;

    public User() {
    }

    public User(int id, String name, String lastName, String speciality) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
    }

    public User(String name, String lastName, String speciality) {
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
    }

}
