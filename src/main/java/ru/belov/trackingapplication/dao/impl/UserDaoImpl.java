package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.UserDao;
import ru.belov.trackingapplication.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, name, last_name, speciality FROM user",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(int id) {
        return jdbcTemplate.query("SELECT id, name, last_name, speciality FROM user WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(User user) {
        jdbcTemplate.update("INSERT INTO user (name, last_name, speciality) VALUES(?, ?, ?)",
                user.getName(), user.getLastName(), user.getSpeciality());
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

}
