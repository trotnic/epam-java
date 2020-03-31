package shop.dao;/*
 * @project lab2
 * @author vladislav on 3/31/20
 */

import java.util.List;

public interface DAO<T> {
    void read();
    void save();
    void update(T older, T newer);
    void delete(T obj);
    List<T> getCache();
    String path();

}
