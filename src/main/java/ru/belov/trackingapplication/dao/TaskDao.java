package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.dto.TaskDto;

public interface TaskDao {

    List<TaskDto> findAll();
    TaskDto findById(int id);
    void add(TaskDto dto);
    void delete(int id);
    List<TaskDto> getTasksInProjects(int id);
    List<TaskDto> getTasksForUser(int id);
}
