package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.PriorityDao;
import ru.belov.trackingapplication.model.Priority;

@Repository
public class PriorityDaoImpl implements PriorityDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PriorityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Priority> findAll() {
        return jdbcTemplate.query("SELECT id, name FROM priority", new BeanPropertyRowMapper<>(Priority.class));
    }

    public Priority findById(int id) {
        return jdbcTemplate.query("SELECT id, name FROM priority WHERE id = ?",
                new BeanPropertyRowMapper<>(Priority.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Priority priority) {
        jdbcTemplate.update("INSERT INTO project(name) VALUE ?", priority.getName());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM project WHERE id = ?", id);
    }

}
