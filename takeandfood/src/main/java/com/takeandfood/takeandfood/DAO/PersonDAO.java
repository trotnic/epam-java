package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PersonDAO implements DAO<Person, String> {


    private final String SQL_INSERT_PERSON = "INSERT INTO PERSON (name, login, password, email, STATUS, ROLE) VALUES (?,?,?,?,?,?);";
    private final String SQL_FIND_PERSON = "SELECT * FROM PERSON WHERE ID = ?";
    private final String SQL_GET_ALL = "SELECT * FROM PERSON";
    private final String SQL_UPDATE_PERSON = "UPDATE PERSON SET NAME = ?, LOGIN = ?, PASSWORD = ?, EMAIL = ?, STATUS = ?, ROLE = ? WHERE ID = ?";


    private Logger log = LogManager.getLogger();

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean create(Person item) {
        boolean result = jdbcTemplate.update("INSERT INTO PERSON (name, login, password, email, role, status) VALUES (?, ?, ?, ?, ?, ?)",
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
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PERSON WHERE ID = ?",
                new Object[]{key},
                (rs, rowNumber) ->
                        Optional.of(
                                new Person.Builder()
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
        return jdbcTemplate.query(
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
                        .build()
        );
    }

    @Override
    public boolean update(Person updated) {
        return jdbcTemplate.update(
                SQL_UPDATE_PERSON,
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
        boolean result = jdbcTemplate.update(
                "DELETE FROM PERSON WHERE ID = ?",
                key
        ) > 0;
        return result;
    }

    public List<Person> getAllRelatedTo(String restaurantID) {
        return jdbcTemplate.query(
                "SELECT * FROM PERSON WHERE RESTAURANT_ID = ?",
                new Object[]{restaurantID},
                (rs, rowNumber) ->
                        new Person.Builder()
                                .withEmail(rs.getString("email"))
                                .withId(rs.getLong("id"))
                                .withLogin(rs.getString("login"))
                                .withName(rs.getString("name"))
                                .withPassword(rs.getString("password"))
                                .withRole(rs.getInt("role"))
                                .withStatus(rs.getInt("status"))
                                .build()
        );
    }

    public boolean updateAdminStatus(String id, String restaurantID) {
        String sql =
                Integer.parseInt(restaurantID) == 0 ?
                        "UPDATE PERSON SET ROLE = 1, RESTAURANT_ID = ? WHERE ID = ?" :
                        "UPDATE PERSON SET ROLE = 0, RESTAURANT_ID = ? WHERE ID = ?";
        return jdbcTemplate.update(
                sql,
                new Object[]{restaurantID, id}
            ) > 0;
    }
}
