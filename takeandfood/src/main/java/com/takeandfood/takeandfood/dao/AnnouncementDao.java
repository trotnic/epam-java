package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementDao implements dao<Announcement, Long> {

    private SessionFactory sessionFactory;

    @Autowired
    public AnnouncementDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Announcement item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Announcement> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = session.load(Announcement.class, key);
        return Optional.of(announcement);
    }

    @Override
    public List<Announcement> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Announcement> announcementList = session.createQuery("from Announcement").list();
        return announcementList;
    }

    @Override
    public void update(Announcement updated) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = session.load(Announcement.class, key);
        session.delete(announcement);
    }
}
