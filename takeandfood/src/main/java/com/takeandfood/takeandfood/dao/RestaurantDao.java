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
public class RestaurantDao implements dao<Restaurant, String> {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Restaurant item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Restaurant> get(String key) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = (Restaurant)session.load(Restaurant.class, Integer.valueOf(key));
        return Optional.of(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Restaurant> restaurantList = session.createQuery("from Restaurant").list();
        return restaurantList;
    }

    @Override
    public void update(Restaurant updated) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updated);
    }

    @Override
    public void delete(String key) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = (Restaurant)session.load(Restaurant.class, Integer.valueOf(key));
        session.delete(restaurant);
    }

//    public Optional<Restaurant> getbyName(String name) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM RESTAURANT WHERE NAME = ?",
//                new Object[]{name},
//                (rs, rowNumber) ->
//                        Optional.of(
//                                new Restaurant.Builder()
//                                        .withName(rs.getString("name"))
//                                        .withId(rs.getLong("id"))
//                                        .build()
//                        )
//        );
//    }
}
