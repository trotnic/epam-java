package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementDAO implements DAO<Announcement, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Announcement> getByOwnerDate(String ownerID, String date) {
        Announcement announcement = jdbcTemplate.queryForObject(
                "SELECT * FROM ANNOUNCEMENT WHERE RESTAURANT_ID = ? AND DATE = ?",
                new Object[]{ownerID, date},
                (rs, rowNumber) ->
                        new Announcement.Builder()
                                .withDate(rs.getString(2))
                                .withID(rs.getLong(1))
                                .withOwnerID(rs.getLong("restaurant_id"))
                                .build()
        );
        return Optional.of(announcement);
    }

    @Override
    public boolean create(Announcement item) {
        return jdbcTemplate.update(
                "INSERT INTO ANNOUNCEMENT (DATE, RESTAURANT_ID) VALUES (?, ?)",
                item.getDate(),
                item.getOwnerID()
        ) > 0;
    }

    @Override
    public Optional<Announcement> get(String key) {

        List<Dish> dishes = jdbcTemplate.query(
                "SELECT * FROM DISH WHERE ANNOUNCEMENT_ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        new Dish.Builder()
                            .withAmount(rs.getLong("amount"))
                            .withAnnouncement(rs.getLong("announcement_id"))
                            .withId(rs.getLong("id"))
                            .withName(rs.getString("name"))
                            .build()
        );

        Announcement announcement = jdbcTemplate.queryForObject(
                "SELECT * FROM ANNOUNCEMENT WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        new Announcement.Builder()
                            .withDate(rs.getString(2))
                            .withID(rs.getLong(1))
                            .withOwnerID(rs.getLong("restaurant_id"))
                            .build()
        );

        announcement.setDishes(dishes);
        return Optional.of(announcement);
    }

    @Override
    public List<Announcement> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM ANNOUNCEMENT",
                (rs, rowNumber) -> {
                    Announcement announcement = new Announcement.Builder()
                            .withDate(rs.getString(2))
                            .withID(rs.getLong(1))
                            .withOwnerID(rs.getLong(3))
                            .build();
                    List<Dish> dishes = jdbcTemplate.query(
                            "SELECT * FROM DISH WHERE ANNOUNCEMENT_ID = ?",
                            new Object[]{announcement.getID()},
                            (rsi, rowNumberi) ->
                                    new Dish.Builder()
                                            .withAmount(rsi.getLong("amount"))
                                            .withAnnouncement(rsi.getLong("announcement_id"))
                                            .withId(rsi.getLong("id"))
                                            .withName(rsi.getString("name"))
                                            .build()
                    );
                    announcement.setDishes(dishes       );
                    return announcement;
                }
        );

    }

    @Override
    public boolean update(Announcement updated) {
        return jdbcTemplate.update(
                "UPDATE ANNOUNCEMENT SET DATE = ?, RESTAURANT_ID = ? WHERE ID = ?",
                updated.getDate(),
                updated.getOwnerID(),
                updated.getID()
        ) > 0;
    }

    @Override
    public boolean delete(String key) {
        return jdbcTemplate.update(
                "DELETE FROM ANNOUNCEMENT WHERE ID = ?",
                key
        ) > 0;
    }
}
