package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.util.List;
import java.util.Optional;

public interface dao<T, K> {
    void create(T item);
    Optional<T> get(K key);
    List<T> getAll();
    T update(T updated);
    void delete(K key);
}
