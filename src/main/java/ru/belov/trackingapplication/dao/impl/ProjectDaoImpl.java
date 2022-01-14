package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.ProjectDao;
import ru.belov.trackingapplication.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll() {
        return jdbcTemplate.query("SELECT id, name FROM project", new BeanPropertyRowMapper<>(Project.class));
    }

    public Project findById(int id) {
        return jdbcTemplate.query("SELECT id, name FROM project WHERE id = ?",
                new BeanPropertyRowMapper<>(Project.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Project project) {
        jdbcTemplate.update("INSERT INTO project (name) VALUES (?)", project.getName());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM project WHERE id = ?", id);
    }

}
