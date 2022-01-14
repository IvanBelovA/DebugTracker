package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.TaskDao;
import ru.belov.trackingapplication.dto.TaskDto;

@Repository
public class TaskDaoImpl implements TaskDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TaskDto> findAll() {
        return jdbcTemplate.query("SELECT id, project_id, topic_id, type_id, priority_id, user_id, description "
                + "FROM task",
                new BeanPropertyRowMapper<>(TaskDto.class));
    }

    public TaskDto findById(int id) {
        return jdbcTemplate.query("SELECT id, project_id, topic_id, type_id, priority_id, user_id, description "
                + "FROM task WHERE id = ?",
                new BeanPropertyRowMapper<>(TaskDto.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(TaskDto dto) {
        jdbcTemplate.update("INSERT INTO task"
                + "(project_id, topic_id, type_id, user_id, priority_id, description) "
                + "VALUES(?, ?, ?, ?, ?, ?)",
                dto.getProjectId(), dto.getTopicId(), dto.getTypeId(), dto.getUserId(),
                dto.getPriorityId(), dto.getDescription());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }

    public List<TaskDto> getTasksInProjects(int id) {
        return jdbcTemplate.query("SELECT * FROM task WHERE project_id = ?",
                new BeanPropertyRowMapper<>(TaskDto.class), id);
    }

    public List<TaskDto> getTasksForUser(int id) {
        return jdbcTemplate.query("SELECT * FROM task WHERE user_id = ?",
                new BeanPropertyRowMapper<>(TaskDto.class), id);
    }

}
