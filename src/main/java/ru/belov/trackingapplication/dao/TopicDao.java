package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.model.Topic;

public interface TopicDao {

    List<Topic> findAll();
    Topic findById(int id);
    void add(Topic topic);
    void delete(int id);
}
