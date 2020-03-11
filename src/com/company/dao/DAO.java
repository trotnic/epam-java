package com.company.dao;

import java.io.*;
import java.util.ArrayList;

public abstract class DAO<T, K> {
    public abstract String path();
    private ArrayList<T> data;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void save() {
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(path()));
            this.outputStream.writeObject(data);
            this.outputStream.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public void load() {
        try {
            this.inputStream = new ObjectInputStream(new FileInputStream(path()));
            this.data = (ArrayList<T>) inputStream.readObject();
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
        this.data = new ArrayList<>();
    }

    public void create(T item) {
        try {
            this.data.add(item);
        } catch (Exception e) {

        }
    }

    public T getById(K key) {
        try {
            for (T item : data) {
                if (item.hashCode() == key.hashCode()) {
                    return item;
                }
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {

        }
        return data.get(0);
    }

    public ArrayList<T> getAll() {
        return this.data;
    }

    public void update(T item) {
        try {
            for (int i = 0; i < this.data.size(); i++) {
                if (this.data.get(i).hashCode() == item.hashCode()) {
                    this.data.set(i, item);
                }
            }
        } catch(Exception e) {

        }
    }

    public T delete(K key) {
        try {
            // Intellij's foreach with a closure
            this.data.removeIf(item -> item.hashCode() == key.hashCode());
        } catch(Exception e) {

        }
    }
}
