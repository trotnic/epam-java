package com.company.dao;

import com.company.models.Announcement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnnouncementDAO extends DAO<Announcement, String> {

    @Override
    public boolean create(Announcement item) throws SQLException {
        String sql = "INSERT INTO ANNOUNCEMENT (ID, RESTAURANT_ID, DATE) VALUES (SQ_ANNOUNCEMENT.nextval, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getOwner().getId());
        statement.setDate(2, java.sql.Date.valueOf(item.getDate()));
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }

    @Override
    public Optional<Announcement> get(String key) throws SQLException {
        String sql = "SELECT * FROM ANNOUNCEMENT WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, key);
        ResultSet resultSet = statement.executeQuery(sql);
        return Optional.of(new Announcement.Builder()
                                .withDate(resultSet.getDate("date"))
                                .withId(resultSet.getLong("id"))
                                .withOwner(resultSet.getLong("restaurant_id"))
                                .build());
    }

    @Override
    public List<Announcement> getAll() throws SQLException {
        List<Announcement> result = new ArrayList<>();
        String sql = "SELECT * FROM ANNOUNCEMENT";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            result.add(new Announcement.Builder()
                        .withDate(resultSet.getDate("date"))
                        .withId(resultSet.getLong("id"))
                        .withOwner(resultSet.getLong("restaurant_id"))
                        .build());
        }
        return result;
    }

    @Override
    public boolean update(Announcement updated) throws SQLException {
        String sql = "UPDATE ANNOUNCEMENT SET date = ?, restaurant_id = ? WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, java.sql.Date.valueOf(updated.getDate()));
        statement.setLong(2, updated.getOwner());
        statement.setLong(3, updated.getId());
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }

    @Override
    public boolean delete(Announcement item) throws SQLException {
        String sql = "DELETE FROM ANNOUNCEMENT WHERE ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, item.getId());
        boolean result = statement.executeUpdate() > 0;
        connection.commit();
        return result;
    }
}
