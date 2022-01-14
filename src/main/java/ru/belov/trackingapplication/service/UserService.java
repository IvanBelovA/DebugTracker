package ru.belov.trackingapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.belov.trackingapplication.dao.UserDao;
import ru.belov.trackingapplication.model.User;

@Service
@Slf4j
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        log.debug("Find all users");
        return userDao.findAll();
    }

    public void add(User user) {
        log.debug("Find user with id = {}", user.getId());
        userDao.add(user);
    }

    public void delete(int id) {
        log.debug("Delete user with id = {}", id);
        userDao.deleteById(id);
    }

}
