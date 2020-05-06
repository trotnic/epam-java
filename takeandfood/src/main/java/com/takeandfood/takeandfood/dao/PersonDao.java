package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PersonDao implements dao<Person, Long> {

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
    public List<Person> getAll(Integer page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Person");
        query.setFirstResult((page-1)*10);
        query.setMaxResults(page*10);
        return query.list();
    }

    @Override
    public Person update(Person updated) {
        Session session = sessionFactory.getCurrentSession();
        return (Person) session.merge(updated);
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, key);
        session.delete(person);
    }
}
