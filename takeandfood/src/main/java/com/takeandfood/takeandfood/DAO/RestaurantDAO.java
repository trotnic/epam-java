package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantDAO implements DAO<Restaurant, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Restaurant item) {
        return jdbcTemplate.update(
            "INSERT INTO RESTAURANT (NAME) VALUES (?)",
                item.getName()
        ) > 0;
    }

    @Override
    public Optional<Restaurant> get(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM RESTAURANT WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(new Restaurant.Builder()
                                .withName(rs.getString("name"))
                                .withId(rs.getLong("id"))
                                .build()
                        )
        );
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM RESTAURANT",
                (rs, rowNumber) ->
                        new Restaurant.Builder()
                        .withName(rs.getString("name"))
                        .withId(rs.getLong("id"))
                        .build()
        );
    }

    @Override
    public boolean update(Restaurant updated) {
        return jdbcTemplate.update(
                "UPDATE RESTAURANT SET NAME = ? WHERE ID = ?",
                updated.getName(),
                updated.getId()
        ) > 0;
    }

    @Override
    public boolean delete(String key) {
        return jdbcTemplate.update(
                "DELETE FROM RESTAURANT WHERE ID = ?",
                key
        ) > 0;
    }
}
