package com.takeandfood.takeandfood.dao;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.util.List;
import java.util.Optional;

public interface dao<T> {
    void create(T item);
    Optional<T> get(Long key);
    List<T> getAll(Integer page);
    T update(T updated);
    void delete(Long key);
}
