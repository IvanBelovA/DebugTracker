package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.TopicDao;
import ru.belov.trackingapplication.model.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TopicDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Topic> findAll() {
        return jdbcTemplate.query("SELECT id, title, description FROM topic",
                new BeanPropertyRowMapper<>(Topic.class));
    }

    public Topic findById(int id) {
        return jdbcTemplate.query("SELECT id, title, description FROM topic WHERE id = ?",
                new BeanPropertyRowMapper<>(Topic.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Topic topic) {
        jdbcTemplate.update("INSERT INTO topic(title, description) VALUE ?, ?",
                topic.getTitle(), topic.getDescription());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM topic WHERE id = ?", id);
    }

}
