package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PersonDAO implements DAO<Person, String> {

    private Logger log = LogManager.getLogger();

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private SessionFactory sessionFactory;

//    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void create(Person item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Person> get(String key) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person)session.load(Person.class, Integer.valueOf(key));
        return Optional.of(person);
    }

    @Override
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> personList = session.createQuery("from Person ").list();
        return personList;
    }

    @Override
    public void update(Person updated) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updated);
    }

    @Override
    public void delete(String key) {
        Session session = sessionFactory.getCurrentSession();
        Person person = (Person)session.load(Person.class, Integer.valueOf(key));
        session.delete(person);
    }

//    public List<Person> getAllRelatedTo(String restaurantID) {
//        return jdbcTemplate.query(
//                "SELECT * FROM PERSON WHERE RESTAURANT_ID = ?",
//                new Object[]{restaurantID},
//                (rs, rowNumber) ->
//                        new Person.Builder()
//                                .withEmail(rs.getString("email"))
//                                .withId(rs.getLong("id"))
//                                .withLogin(rs.getString("login"))
//                                .withName(rs.getString("name"))
//                                .withPassword(rs.getString("password"))
//                                .withRole(rs.getInt("role"))
//                                .withStatus(rs.getInt("status"))
//                                .build()
//        );
//    }
//
//    public boolean updateAdminStatus(String id, String restaurantID) {
//        String sql =
//                Integer.parseInt(restaurantID) == 0 ?
//                        "UPDATE PERSON SET ROLE = 1, RESTAURANT_ID = ? WHERE ID = ?" :
//                        "UPDATE PERSON SET ROLE = 0, RESTAURANT_ID = ? WHERE ID = ?";
//        return jdbcTemplate.update(
//                sql,
//                new Object[]{restaurantID, id}
//            ) > 0;
//    }
}
