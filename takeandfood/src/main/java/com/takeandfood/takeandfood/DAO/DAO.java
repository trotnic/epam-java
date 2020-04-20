package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {
    boolean create(T item) throws SQLException;
    Optional<T> get(K key) throws SQLException;
    List<T> getAll() throws SQLException;
    boolean update(T updated) throws SQLException;
    boolean delete(T item) throws SQLException;
}
