package ru.belov.trackingapplication.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.belov.trackingapplication.dao.TypeDao;
import ru.belov.trackingapplication.model.Type;

@Repository
public class TypeDaoImpl implements TypeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TypeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Type> findAll() {
        return jdbcTemplate.query("SELECT id, title FROM type",
                new BeanPropertyRowMapper<>(Type.class));
    }

    public Type findById(int id) {
        return jdbcTemplate.query("SELECT id, title FROM type WHERE id = ?",
                new BeanPropertyRowMapper<>(Type.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Type type) {
        jdbcTemplate.update("INSERT INTO type (title) VALUE ?", type.getTitle());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM type WHERE id = ?", id);
    }

}
