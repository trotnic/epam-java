package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.beans.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackDAO implements DAO<Feedback, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Feedback item) {
        return jdbcTemplate.update(
                "INSERT INTO FEEDBACK (TEXT, USER_ID, DATE, RESTAURANT_ID) " +
                    "VALUES (?,?,?,?)",
                item.getText(),
                item.getUserID(),
                item.getDate(),
                item.getRestaurantID()
        ) > 0;
    }

    @Override
    public Optional<Feedback> get(String key) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM FEEDBACK WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(
                                new Feedback.Builder()
                                .withDate(rs.getString("date"))
                                .withRestaurantID(rs.getLong("restaurant_id"))
                                .withText(rs.getString("text"))
                                .withUserID(rs.getLong("user_id"))
                                .withID(rs.getLong("id"))
                                .build()
                        )
        );
    }

    @Override
    public List<Feedback> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM FEEDBACK",
                (rs, rowNumber) ->
                        new Feedback.Builder()
                        .withDate(rs.getString("date"))
                        .withRestaurantID(rs.getLong("restaurant_id"))
                        .withText(rs.getString("text"))
                        .withUserID(rs.getLong("user_id"))
                        .withID(rs.getLong("id"))
                        .build()
        );
    }

    @Override
    public boolean update(Feedback updated) {
        return jdbcTemplate.update(
                "UPDATE FEEDBACK SET TEXT = ?, USER_ID = ?, RESTAURANT_ID = ?, DATE = ? WHERE ID = ?",
                updated.getText(),
                updated.getId(),
                updated.getRestaurantID(),
                updated.getDate(),
                updated.getId()
        ) > 0;
    }

    @Override
    public boolean delete(String key) {
        return jdbcTemplate.update(
                "DELETE FROM FEEDBACK WHERE ID = ?",
                key
        ) > 0;
    }

    public List<Feedback> getAllRelatedTo(String id) {
        return jdbcTemplate.query(
                "SELECT * FROM FEEDBACK WHERE RESTAURANT_ID = ?",
                new Object[]{id},
                (rs, rowNumber) ->
                        new Feedback.Builder()
                                .withDate(rs.getString("date"))
                                .withRestaurantID(rs.getLong("restaurant_id"))
                                .withText(rs.getString("text"))
                                .withUserID(rs.getLong("user_id"))
                                .withID(rs.getLong("id"))
                                .build()
        );
    }
}
