package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import org.aspectj.org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementDao implements dao<Announcement> {

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
        return session.byId(Announcement.class).loadOptional(key);
    }

    @Override
    public List<Announcement> getAll(Integer page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Announcement ");
        query.setFirstResult((page - 1) * 10);
        query.setMaxResults(page * 40);
        return (List<Announcement>) query.list();
    }

    @Override
    public Announcement update(Announcement updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Announcement) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = session.load(Announcement.class, key);
        session.delete(announcement);
    }
}
