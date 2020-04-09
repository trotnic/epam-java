package com.company.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import oracle.jdbc.OracleDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public abstract class DAO<T, K> {
    static Logger log = LogManager.getLogger();
    public abstract boolean create(T item) throws SQLException;
    public abstract Optional<T> get(K key) throws SQLException;
    public abstract List<T> getAll() throws  SQLException;
    public abstract boolean update(T updated) throws SQLException;
    public abstract boolean delete(T item) throws SQLException;

//    public abstract String path();
//    private HashSet<T> cache;
//    private ObjectInputStream inputStream;
//    private ObjectOutputStream outputStream;

    protected Connection connection;

    public DAO()  {

        String dbURL1 = "jdbc:oracle:thin:sys/oracle@localhost:1521:takeandfood";

        try {
            connection = DriverManager.getConnection(dbURL1);
            if(connection != null) {
                log.info("Successfully connected: " + connection);
            }
        } catch (SQLException ex) {
            log.info(ex);
        }
    }

//    public void save() {
//        try {
//            this.outputStream = new ObjectOutputStream(new FileOutputStream(path()));
//            this.outputStream.writeObject(cache);
//            this.outputStream.close();
//            log.info("Objects saved. Amount: " + cache.size());
//        } catch (IOException e) {
//            log.info(e);
//        }
//    }
//
//    public void load() {
//        try {
//            this.inputStream = new ObjectInputStream(new FileInputStream(path()));
//            this.cache = (HashSet<T>) inputStream.readObject();
//            this.inputStream.close();
//            log.info("Objects loaded. Amount: " + cache.size());
//        } catch (ClassNotFoundException | IOException e) {
//            log.info(e);
//        }
//    }

//    public DAO() {
//        this.cache = new HashSet<>();
//    }

//    public void create(T item) {
//        try {
//            this.cache.add(item);
//            log.info("Created object: " + item);
//        } catch (Exception e) {
//            log.info(e);
//        }
//    }
//
//TODO
//    public T getByKey(K key) {
//        try {
//            for (T item : cache) {
//                if (item.hashCode() == key.hashCode()) {
//                    return item;
//                }
//            }
//            throw new IllegalArgumentException();
//        } catch (IllegalArgumentException e) {
//            log.info("Object not found. " + e);
//        }
//        return cache.get(0);
//    }
//
//    public HashSet<T> getAll() { return cache; }
//
//TODO
//    public T updateOrCreate(T item) {
//        try {
//            for (int i = 0; i < this.cache.size(); i++) {
//                if (this.cache.get(i).hashCode() == item.hashCode()) {
//                    this.cache.set(i, item);
//                    return this.cache.get(i);
//                }
//            }
//        } catch(Exception e) {
//            log.info("Object not found. " + e);
//        }
//        create(item);
//        return item;
//    }
//
//    public void delete(K key) {
//        try {
//            // Intellij's foreach with a closure
//            this.cache.removeIf(item -> item.hashCode() == key.hashCode());
//            log.info("Object removed.");
//        } catch(Exception e) {
//            log.info("Object not found.");
//        }
//    }
}
