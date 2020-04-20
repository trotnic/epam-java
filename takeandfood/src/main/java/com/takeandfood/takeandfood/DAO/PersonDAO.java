package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import javax.sql.DataSource;

import com.takeandfood.takeandfood.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//public class PersonDAO implements DAO<Person, String> {
//}

@Repository
public class PersonDAO implements DAO<Person, String> {


    private final String SQL_INSERT_PERSON = "INSERT INTO PERSON (name, login, password, email, STATUS, ROLE) VALUES (?,?,?,?,?,?);";
    private final String SQL_FIND_PERSON = "SELECT * FROM PERSON WHERE ID = ?";
    private final String SQL_GET_ALL = "SELECT * FROM PERSON";


    private Logger log = LogManager.getLogger();

//    private Connection connection;
//
    @Autowired
    private JdbcTemplate template;

//    @Override
//    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean create(Person item) {
        boolean result = template.update("INSERT INTO PERSON (name, login, password, email, role, status) VALUES (?, ?, ?, ?, ?, ?)",
                item.getName(),
                item.getLogin(),
                item.getPassword(),
                item.getEmail(),
                item.getRole(),
                item.getStatus()) > 0;
        return result;
    }

    @Override
    public Optional<Person> get(String key) {
        return template.queryForObject(
                "SELECT * FROM PERSON WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(new Person.Builder()
                                .withEmail(rs.getString("email"))
                                .withId(rs.getLong("id"))
                                .withLogin(rs.getString("login"))
                                .withName(rs.getString("name"))
                                .withPassword(rs.getString("password"))
                                .withRole(rs.getInt("role"))
                                .withStatus(rs.getInt("status"))
                                .build()
                        ));
    }

    @Override
    public List<Person> getAll() {
        return template.query(
                SQL_GET_ALL,
                (rs, rowNumber) ->
                        new Person.Builder()
                        .withEmail(rs.getString("email"))
                        .withId(rs.getLong("id"))
                        .withLogin(rs.getString("login"))
                        .withName(rs.getString("name"))
                        .withPassword(rs.getString("password"))
                        .withRole(rs.getInt("role"))
                        .withStatus(rs.getInt("status"))
                        .build());
    }

    @Override
    public boolean update(Person updated) {
        return template.update("UPDATE PERSON SET NAME = ?, LOGIN = ?, PASSWORD = ?, EMAIL = ?, STATUS = ?, ROLE = ? WHERE ID = ?",
                updated.getName(),
                updated.getLogin(),
                updated.getPassword(),
                updated.getEmail(),
                updated.getStatus().ordinal(),
                updated.getRole().ordinal(),
                updated.getId()) > 0;
    }

    @Override
    public boolean delete(String key) {
        boolean result = template.update("DELETE FROM PERSON WHERE ID = ?", key) > 0;
        return result;
    }
}
