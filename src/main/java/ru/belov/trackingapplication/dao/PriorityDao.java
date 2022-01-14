package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.model.Priority;

public interface PriorityDao {

    List<Priority> findAll();
    Priority findById(int id);
    void add(Priority priority);
    void delete(int id);
}
