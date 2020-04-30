package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Person;
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
        Restaurant restaurant = jdbcTemplate.queryForObject(
                "SELECT * FROM RESTAURANT WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        new Restaurant.Builder()
                                .withName(rs.getString("name"))
                                .withId(rs.getLong("id"))
                                .build()
        );
        List<Person> administrators = jdbcTemplate.query(
                "SELECT * FROM PERSON WHERE RESTAURANT_ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        new Person.Builder()
                                .withEmail(rs.getString("email"))
                                .withId(rs.getLong("id"))
                                .withLogin(rs.getString("login"))
                                .withName(rs.getString("name"))
                                .withPassword(rs.getString("password"))
                                .withRole(rs.getInt("role"))
                                .withStatus(rs.getInt("status"))
                                .build()
        );
        restaurant.setAdministrators(administrators);
        return Optional.of(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM RESTAURANT",
                (rs, rowNumber) -> {
                    Restaurant restaurant = new Restaurant.Builder()
                            .withName(rs.getString("name"))
                            .withId(rs.getLong("id"))
                            .build();
                    List<Person> administrators = jdbcTemplate.query(
                            "SELECT * FROM PERSON WHERE RESTAURANT_ID = ?",
                            new Object[]{restaurant.getId()},
                            (rsi, rowNumberi) ->
                                    new Person.Builder()
                                            .withEmail(rsi.getString("email"))
                                            .withId(rsi.getLong("id"))
                                            .withLogin(rsi.getString("login"))
                                            .withName(rsi.getString("name"))
                                            .withPassword(rsi.getString("password"))
                                            .withRole(rsi.getInt("role"))
                                            .withStatus(rsi.getInt("status"))
                                            .build()
                    );
                    restaurant.setAdministrators(administrators);
                    return restaurant;
                }
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

    public Optional<Restaurant> getbyName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM RESTAURANT WHERE NAME = ?",
                new Object[]{name},
                (rs, rowNumber) ->
                        Optional.of(
                                new Restaurant.Builder()
                                        .withName(rs.getString("name"))
                                        .withId(rs.getLong("id"))
                                        .build()
                        )
        );
    }
}
