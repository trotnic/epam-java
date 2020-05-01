package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantDao implements dao<Restaurant, Long> {

    private SessionFactory sessionFactory;

    @Autowired
    public RestaurantDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Restaurant item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Restaurant> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = session.load(Restaurant.class, key);
        System.out.println("restaurant: " + restaurant);
        return Optional.of(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Restaurant").list();
    }

    @Override
    public Restaurant update(Restaurant updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Restaurant) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = session.load(Restaurant.class, key);
        session.delete(restaurant);
    }
}
