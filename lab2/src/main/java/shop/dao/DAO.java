package shop.dao;/*
 * @project lab2
 * @author vladislav on 3/31/20
 */

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    void read();
    void save();
    void update(T older, T newer);
    void delete(T obj);
    Optional<T> getByName(String name);
    List<T> getCache();
    String path();

}
