package com.company.dao;

import com.company.models.Dish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishDAO extends DAO<Dish, String> {
    @Override
    public boolean create(Dish item) throws SQLException {
        String sql = "INSERT INTO DISH (ID, ANNOUNCEMENT, NAME, AMOUNT) VALUES (SQ_DISH.nextval, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getAnnouncementId());
        statement.setString(2, item.getName());
        statement.setString(3, item.getAmount());
        boolean result = statement.executeUpdate() > 0;
        if(result) {
            log.info("A new dish was inserted successfully");
        }
        connection.commit();
        return result;
    }

    @Override
    public Optional<Dish> get(String key) throws SQLException {
        String sql = "SELECT * FROM DISH WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, key);
        ResultSet result = statement.executeQuery(sql);
        return Optional.of(new Dish.Builder()
                            .withAmount(result.getString("amount"))
                            .withAnnouncement(result.getLong("announcement_id"))
                            .withId(result.getLong("id"))
                            .withName(result.getString("name"))
                            .build());
    }

    @Override
    public List<Dish> getAll() throws SQLException {
        List<Dish> result = new ArrayList<>();
        String sql = "SELECT * FROM DISH";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            result.add(new Dish.Builder()
                .withAmount(resultSet.getString("amount"))
                .withAnnouncement(resultSet.getLong("announcement_id"))
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .build());
        }
        return result;
    }

    @Override
    public boolean update(Dish updated) throws SQLException {
        String sql = "UPDATE DISH SET announcement_id = ?, name = ?, amount = ? WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1,updated.getAnnouncementId());
        statement.setString(2,updated.getName());
        statement.setString(3, updated.getAmount());
        statement.setLong(4, updated.getId());
        boolean result = statement.executeUpdate() > 0;
        if(result) {
            log.info("Successfully updated: " + updated);
        }
        connection.commit();
        return result;
    }

    @Override
    public boolean delete(Dish item) throws SQLException {
        String sql = "DELETE FROM DISH WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getId());
        return statement.executeUpdate() > 0;
    }
}
