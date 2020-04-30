package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DishDAO implements DAO<Dish, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean create(Dish item) {
        System.out.println(item);
        jdbcTemplate.update(
                "INSERT INTO DISH (NAME, AMOUNT, ANNOUNCEMENT_ID) VALUES (?,?,?)",
                item.getName(),
                item.getAmount(),
                item.getAnnouncementID()
        );
        return true;
    }

    @Override
    public Optional<Dish> get(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM DISH WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(
                                new Dish.Builder()
                                .withAmount(rs.getLong("amount"))
                                .withAnnouncement(rs.getLong("announcement_id"))
                                .withName(rs.getString("name"))
                                .withId(rs.getLong("id"))
                                .build()
                        )
        );
    }

    @Override
    public List<Dish> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM DISH",
                (rs, rowNumber) ->
                        new Dish.Builder()
                        .withAmount(rs.getLong("amount"))
                        .withAnnouncement(rs.getLong("announcement_id"))
                        .withName(rs.getString("name"))
                        .withId(rs.getLong("id"))
                        .build()
        );
    }

    @Override
    public boolean update(Dish updated) {
        return jdbcTemplate.update(
                "UPDATE DISH SET NAME = ?, AMOUNT = ?, ANNOUNCEMENT_ID = ?",
                updated.getName(),
                updated.getAmount(),
                updated.getAnnouncementID()
        ) > 0;
    }

    @Override
    public boolean delete(String key) {
        return jdbcTemplate.update(
                "DELETE FROM DISH WHERE ID = ?",
                key
        ) > 0;
    }

    public List<Dish> allRelatedTo(String id) {
        return jdbcTemplate.query(
                "SELECT * FROM DISH WHERE ANNOUNCEMENT_ID = ?",
                new Object[]{id},
                (rs, rowNumber) ->
                        new Dish.Builder()
                                .withAmount(rs.getLong("amount"))
                                .withAnnouncement(rs.getLong("announcement_id"))
                                .withName(rs.getString("name"))
                                .withId(rs.getLong("id"))
                                .build()
        );
    }
}
