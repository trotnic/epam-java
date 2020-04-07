package com.company.models;

import java.util.*;

public class Announcement extends Model {
    private String id;
    private Restaurant owner;
    private HashSet<Dish> dishes;
    private Date date;

    public Announcement() {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.dishes = new HashSet<>();
    }

    public Announcement(Restaurant owner) {
        this();
        this.owner = owner;
    }

    public String getId() { return id; };
    public Restaurant getOwner() {
        return owner;
    }
    public HashSet<Dish> getPositions() {
        return dishes;
    }
    public Date getDate() {
        return date;
    }

    public void setOwner(Restaurant owner) { this.owner = owner; }
    public void addPosition(Dish dish) { dishes.add(dish); }
    public void setPositions(HashSet<Dish> dishes) { this.dishes = dishes; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("owner=" + owner)
                .add("positions=" + dishes)
                .add("date=" + date)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return getId().equals(that.getId()) &&
                getOwner().equals(that.getOwner()) &&
                Objects.equals(dishes, that.dishes) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwner(), dishes, getDate());
    }
}
