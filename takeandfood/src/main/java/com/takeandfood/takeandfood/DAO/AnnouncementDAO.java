package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementDAO implements DAO<Announcement, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Announcement item) {
        boolean result = jdbcTemplate.update(
                "INSERT INTO ANNOUNCEMENT (DATE) VALUES (?)",
                item.getDate().toString()
        ) > 0;
        return false;
    }

    @Override
    public Optional<Announcement> get(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ANNOUNCEMENT WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(new Announcement.Builder()
                                .withDate(rs.getString(2))
                                .withId(rs.getLong(1))
                                .withOwnerID(rs.getLong("restaurant_id"))
                                .build()
                        )
        );
    }

    @Override
    public List<Announcement> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM ANNOUNCEMENT",
                (rs, rowNumber) ->
                        new Announcement.Builder()
                        .withDate(rs.getString(2))
                        .withId(rs.getLong(1))
                        .withOwnerID(rs.getLong(3))
                        .build()
        );

    }

    @Override
    public boolean update(Announcement updated) {
        return jdbcTemplate.update(
                "UPDATE ANNOUNCEMENT SET DATE = ?, RESTAURANT_ID = ? WHERE ID = ?",
                updated.getDate(),
                updated.getOwnerID(),
                updated.getId()
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
