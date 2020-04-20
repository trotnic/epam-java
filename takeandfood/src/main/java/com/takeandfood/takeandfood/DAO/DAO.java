package com.takeandfood.takeandfood.DAO;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, K> {
    boolean create(T item);
    Optional<T> get(K key);
    List<T> getAll();
    boolean update(T updated);
    boolean delete(K key);
}
