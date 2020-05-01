package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.beans.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackDao implements dao<Feedback, String> {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Feedback item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Feedback> get(String key) {
        Session session = sessionFactory.getCurrentSession();
        Feedback feedback = (Feedback)session.load(Feedback.class, Integer.valueOf(key));
        return Optional.of(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Feedback> feedbackList = session.createQuery("from Feedback ").list();
        return feedbackList;
    }

    @Override
    public void update(Feedback updated) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updated);
    }

    @Override
    public void delete(String key) {
        Session session = sessionFactory.getCurrentSession();
        Feedback feedback = (Feedback)session.load(Feedback.class, Integer.valueOf(key));
        session.delete(feedback);
    }

//    public List<Feedback> getAllRelatedTo(String id) {
//        return jdbcTemplate.query(
//                "SELECT * FROM FEEDBACK WHERE RESTAURANT_ID = ?",
//                new Object[]{id},
//                (rs, rowNumber) ->
//                        new Feedback.Builder()
//                                .withDate(rs.getString("date"))
//                                .withRestaurantID(rs.getLong("restaurant_id"))
//                                .withText(rs.getString("text"))
//                                .withUserID(rs.getLong("user_id"))
//                                .withID(rs.getLong("id"))
//                                .build()
//        );
//    }
}
