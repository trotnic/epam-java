package com.company.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public abstract class DAO<T, K> {
    static Logger log = LogManager.getLogger();
    public abstract String path();
    private ArrayList<T> cache;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void save() {
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(path()));
            this.outputStream.writeObject(cache);
            this.outputStream.close();
            log.info("Objects saved. Amount: " + cache.size());
        } catch (IOException e) {
            log.info(e);
        }
    }

    public void load() {
        try {
            this.inputStream = new ObjectInputStream(new FileInputStream(path()));
            this.cache = (ArrayList<T>) inputStream.readObject();
            this.inputStream.close();
            log.info("Objects loaded. Amount: " + cache.size());
        } catch (ClassNotFoundException | IOException e) {
            log.info(e);
        }
    }

    public DAO() {
        this.cache = new ArrayList<>();
    }

    public void create(T item) {
        try {
            this.cache.add(item);
            log.info("Created object: " + item);
        } catch (Exception e) {
            log.info(e);
        }
    }

    public T getByKey(K key) {
        try {
            for (T item : cache) {
                if (item.hashCode() == key.hashCode()) {
                    return item;
                }
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            log.info("Object not found. " + e);
        }
        return cache.get(0);
    }

    public ArrayList<T> getAll() {
        return this.cache;
    }

    public T updateOrCreate(T item) {
        try {
            for (int i = 0; i < this.cache.size(); i++) {
                if (this.cache.get(i).hashCode() == item.hashCode()) {
                    this.cache.set(i, item);
                    return this.cache.get(i);
                }
            }
        } catch(Exception e) {
            log.info("Object not found. " + e);
        }
        create(item);
        return item;
    }

    public void delete(K key) {
        try {
            // Intellij's foreach with a closure
            this.cache.removeIf(item -> item.hashCode() == key.hashCode());
            log.info("Object removed.");
        } catch(Exception e) {
            log.info("Object not found.");
        }
    }
}
