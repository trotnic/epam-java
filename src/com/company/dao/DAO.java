package com.company.dao;

import java.io.*;
import java.util.ArrayList;

public abstract class DAO<T, K> {
    public abstract String path();
    private ArrayList<T> cache;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void save() {
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(path()));
            this.outputStream.writeObject(cache);
            this.outputStream.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public void upload() {
        try {
            this.inputStream = new ObjectInputStream(new FileInputStream(path()));
            this.cache = (ArrayList<T>) inputStream.readObject();
            this.inputStream.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
    }

    public DAO() {
        this.cache = new ArrayList<>();
    }

    public void create(T item) {
        try {
            this.cache.add(item);
        } catch (Exception e) {

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

        }
        return cache.get(0);
    }

    public ArrayList<T> getAll() {
        return this.cache;
    }

    public void update(T item) {
        try {
            for (int i = 0; i < this.cache.size(); i++) {
                if (this.cache.get(i).hashCode() == item.hashCode()) {
                    this.cache.set(i, item);
                }
            }
        } catch(Exception e) {

        }
    }

    public void delete(K key) {
        try {
            // Intellij's foreach with a closure
            this.cache.removeIf(item -> item.hashCode() == key.hashCode());
        } catch(Exception e) {

        }
    }
}
