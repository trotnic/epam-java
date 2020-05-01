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
public class AnnouncementDao implements dao<Announcement, String> {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

//    public Optional<Announcement> getByOwnerDate(String ownerID, String date) {
//        Announcement announcement = jdbcTemplate.queryForObject(
//                "SELECT * FROM ANNOUNCEMENT WHERE RESTAURANT_ID = ? AND DATE = ?",
//                new Object[]{ownerID, date},
//                (rs, rowNumber) ->
//                        new Announcement.Builder()
//                                .withDate(rs.getString(2))
//                                .withID(rs.getLong(1))
//                                .withOwnerID(rs.getLong("restaurant_id"))
//                                .build()
//        );
//        return Optional.of(announcement);
//    }

    @Override
    public void create(Announcement item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Announcement> get(String key) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = (Announcement)session.load(Announcement.class, Integer.valueOf(key));
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
    public void delete(String key) {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = (Announcement)session.load(Announcement.class, Integer.valueOf(key));
        session.delete(announcement);
    }
}
