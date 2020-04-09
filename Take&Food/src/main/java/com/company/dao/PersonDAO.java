package com.company.dao;

import com.company.models.Person;
import oracle.ucp.proxy.annotation.Pre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAO extends DAO<Person, String> {
    public PersonDAO() throws ClassNotFoundException {
    }

    @Override
    public boolean create(Person item) throws SQLException {
        String sql = "INSERT INTO PERSON (id, name, login, password, email, role, social_status) VALUES (SQ_PERSON.nextval, ?, ?, ?, ?, ?, ?)";
//        String sql = "INSERT INTO PERSON VALUES (SQ_PERSON.nextval, 'Chary', 'Mess', '112 Yellow Hill', 'LOLKEK')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.setString(2, item.getLogin());
        statement.setString(3, item.getPassword());
        statement.setString(4, item.getEmail());
        statement.setInt(5, item.getRole().ordinal());
        statement.setInt(6, item.getStatus().ordinal());
        boolean result = statement.executeUpdate() > 0;
        if (result) {
            log.info("A new user was inserted successfully!");
        }
        connection.commit();
        return result;
    }

    @Override
    public Optional<Person> get(String key) throws SQLException {
        String sql = "SELECT * FROM PERSON WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,key);
        ResultSet result = statement.executeQuery(sql);
        return Optional.of(new Person.Builder()
                                .withEmail(result.getString("EMAIL"))
                                .withId(result.getLong("ID"))
                                .withLogin(result.getString("LOGIN"))
                                .withPassword(result.getString("PASSWORD"))
                                .withName(result.getString("NAME"))
                                .withRole(result.getInt("ROLE"))
                                .withStatus(result.getInt("SOCIAL_STATUS"))
                                .build());
    }

    @Override
    public List<Person> getAll() throws SQLException {
        List<Person> result = new ArrayList<>();
        String sql = "SELECT * FROM PERSON";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            result.add(new Person.Builder()
                            .withEmail(resultSet.getString("EMAIL"))
                            .withId(resultSet.getLong("ID"))
                            .withLogin(resultSet.getString("LOGIN"))
                            .withPassword(resultSet.getString("PASSWORD"))
                            .withName(resultSet.getString("NAME"))
                            .withRole(resultSet.getInt("ROLE"))
                            .withStatus(resultSet.getInt("SOCIAL_STATUS"))
                            .build());
        }
        return result;
    }

    @Override
    public boolean update(Person updated) throws SQLException {
        String sql = "UPDATE PERSON SET name = ?, login = ?, email = ?, password = ?, role = ?, social_status = ?" +
                "WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, updated.getName());
        statement.setString(2, updated.getLogin());
        statement.setString(3, updated.getEmail());
        statement.setString(4, updated.getPassword());
        statement.setInt(5, updated.getRole().ordinal());
        statement.setInt(6, updated.getStatus().ordinal());
        statement.setLong(7, updated.getId());
        boolean result = statement.executeUpdate() > 0;
        if(result) {
            log.info("Successfully updated: " + updated);
        }
        connection.commit();
        return result;
    }

    @Override
    public boolean delete(Person item) throws SQLException {
        String sql = "DELETE FROM PERSON WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getId());
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }
}
