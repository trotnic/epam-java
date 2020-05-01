package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DishDao implements dao<Dish, Long> {

    private SessionFactory sessionFactory;

    @Autowired
    public DishDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Dish item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Dish> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = session.load(Dish.class, key);
        return Optional.of(dish);
    }

    @Override
    public List<Dish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Dish>) session.createQuery("from Dish ").list();
    }

    @Override
    public Dish update(Dish updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Dish) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = session.load(Dish.class, key);
        session.delete(dish);
    }
}
