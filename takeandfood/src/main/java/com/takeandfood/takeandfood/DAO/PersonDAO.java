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
    public boolean create(Person item) throws SQLException {
        System.out.println("HERE");

//        DefaultTransactionDefinition paramTransactionDefinition = new    DefaultTransactionDefinition();
//
//        TransactionStatus status=platformTransactionManager.getTransaction(paramTransactionDefinition );

//        System.out.println(template.update("INSERT INTO PERSON (name, login, password, email, STATUS, ROLE) VALUES (\"Vladislav\", \"tr\", \"qwerty\", \"sobaka@gmail.com\", 1, 1);"));

//        template.update("INSERT INTO PERSON (id, name, login, password, email, role, socialstatus) VALUES (SQ_PERSON.nextval, , ?, ?, ?, ?, ?)",
//                , Map{})
        template.update("INSERT INTO PERSON (name, login, password, email, role, status) VALUES (?, ?, ?, ?, ?, ?)",
                item.getName(),
                item.getLogin(),
                item.getPassword(),
                item.getEmail(),
                1,1);
//        template.update("INSERT INTO PERSON (name, login, password, email, STATUS, ROLE) VALUES (\"Vladislav\", \"troa\", \"qwerty\", \"sobaka@gmail.com\", 1, 1);");
//
        return true;
    }

    @Override
    public Optional<Person> get(String key) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(Person updated) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Person item) throws SQLException {
        return false;
    }
}
