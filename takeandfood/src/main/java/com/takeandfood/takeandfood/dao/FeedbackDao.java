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
public class FeedbackDao implements dao<Feedback, Long> {

    private SessionFactory sessionFactory;

    @Autowired
    public FeedbackDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Feedback item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Feedback> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Feedback feedback = session.load(Feedback.class, key);
        return Optional.of(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Feedback ").list();
    }

    @Override
    public Feedback update(Feedback updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Feedback) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Feedback feedback = session.load(Feedback.class, key);
        session.delete(feedback);
    }
}
