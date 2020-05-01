package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PersonDao implements dao<Person, Long> {

//    private Logger log = LogManager.getLogger();

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Person item) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public Optional<Person> get(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, key);
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
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, key);
        session.delete(person);
    }
}
