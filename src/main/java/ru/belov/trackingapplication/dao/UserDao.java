package ru.belov.trackingapplication.dao;

import java.util.List;

import ru.belov.trackingapplication.model.User;

public interface UserDao {

    List<User> findAll();
    User findById(int id);
    void add(User user);
    void deleteById(int id);

}
