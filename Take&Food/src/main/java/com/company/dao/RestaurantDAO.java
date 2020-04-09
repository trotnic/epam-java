package com.company.dao;

import com.company.models.Restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantDAO extends DAO<Restaurant, String> {

    @Override
    public boolean create(Restaurant item) throws SQLException {
        String sql = "INSERT INTO RESTAURANT (id, name) VALUES (SQ_RESTAURANT.nextval, ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, item.getName());
        boolean result = statement.executeUpdate() > 0;
        return result;
    }

    @Override
    public Optional<Restaurant> get(String key) throws SQLException {
        String sql = "SELECT * FROM RESTAURANT WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, key);
        ResultSet resultSet = statement.executeQuery(sql);
        return Optional.of(new Restaurant.Builder()
                            .withId(resultSet.getLong("id"))
                            .withName(resultSet.getString("name"))
                            .build());
    }

    @Override
    public List<Restaurant> getAll() throws SQLException {
        List<Restaurant> result = new ArrayList<>();
        String sql = "SELECT * FROM RESTAURANT";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            result.add(new Restaurant.Builder()
                            .withName(resultSet.getString("name"))
                            .withId(resultSet.getLong("id"))
                            .build());
        }
        return result;
    }

    @Override
    public boolean update(Restaurant updated) throws SQLException {
        String sql = "UPDATE RESTAURANT SET name = ? WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, updated.getName());
        statement.setLong(2, updated.getId());
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }

    @Override
    public boolean delete(Restaurant item) throws SQLException {
        String sql = "DELETE FROM RESTAURANT WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getId());
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }
}
