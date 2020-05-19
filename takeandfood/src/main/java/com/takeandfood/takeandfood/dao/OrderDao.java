package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import com.takeandfood.takeandfood.beans.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao implements dao<Order> {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Order item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Order> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, key);
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll(Integer page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order");
        query.setFirstResult((page - 1)*10);
        query.setMaxResults(page*10);
        return (List<Order>) query.list();
    }

    @Override
    public Order update(Order updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Order) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, key);
        session.delete(order);
    }

    public List<Order> getForRestaurant(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where restaurant.id = :id and status = 0");
        query.setParameter("id", id);
        return (List<Order>) query.list();
    }

    public List<Order> getForPerson(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where person.id = :id");
        query.setParameter("id", id);
        return (List<Order>) query.list();
    }

    public void deleteWithAnnouncement(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Order where announcement.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
