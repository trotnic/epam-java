package com.company.models;

import java.util.*;

public class Announcement extends Model {
    private Long id;
    private Long owner;
//    private Restaurant owner;
    private HashSet<Dish> dishes;
    private Date date;

    public Announcement() {
//        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.dishes = new HashSet<>();
    }

//    public Announcement(Restaurant owner) {
//        this();
//        this.owner = owner;
//    }

    public Long getId() { return id; };
    public Long getOwner() { return owner; }
//    public Restaurant getOwner() {
//        return owner;
//    }
    public HashSet<Dish> getPositions() {
        return dishes;
    }
    public String getDate() {
        return date.toString();
    }


    public void setId(Long id) { this.id = id; }
    public void setOwner(Long ownerId) { this.owner = ownerId; }
//    public void setOwner(Restaurant owner) { this.owner = owner; }
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

    public static class Builder {
        private Announcement entity;

        public Builder() {
            entity = new Announcement();
        }

        public Builder withOwner(Long id) {
            entity.setOwner(id);
            return this;
        }

        public Builder withDate(Date date) {
            entity.setDate(date);
            return this;
        }

        public Builder withId(Long id) {
            entity.setId(id);
            return this;
        }

        public Announcement build() {
            return entity;
        }
    }
}
