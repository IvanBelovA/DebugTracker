package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.model.Type;

public interface TypeDao {

    List<Type> findAll();
    Type findById(int id);
    void add(Type type);
    void delete(int id);
}
