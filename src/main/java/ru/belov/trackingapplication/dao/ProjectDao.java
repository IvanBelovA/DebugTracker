package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.model.Project;

public interface ProjectDao {

    List<Project> findAll();
    Project findById(int id);
    void add(Project project);
    void delete(int id);
}
