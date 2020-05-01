package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DishDAO implements DAO<Dish, String> {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(Dish item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Dish> get(String key) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = (Dish)session.load(Dish.class, Integer.valueOf(key));
        return Optional.of(dish);
    }

    @Override
    public List<Dish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Dish> dishList = session.createQuery("from Dish ").list();
        return dishList;
    }

    @Override
    public void update(Dish updated) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updated);
    }

    @Override
    public void delete(String key) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = (Dish)session.load(Dish.class, Integer.valueOf(key));
        session.delete(dish);
    }

//    public List<Dish> allRelatedTo(String id) {
//        return jdbcTemplate.query(
//                "SELECT * FROM DISH WHERE ANNOUNCEMENT_ID = ?",
//                new Object[]{id},
//                (rs, rowNumber) ->
//                        new Dish.Builder()
//                                .withAmount(rs.getLong("amount"))
//                                .withAnnouncement(rs.getLong("announcement_id"))
//                                .withName(rs.getString("name"))
//                                .withId(rs.getLong("id"))
//                                .build()
//        );
//    }
}
