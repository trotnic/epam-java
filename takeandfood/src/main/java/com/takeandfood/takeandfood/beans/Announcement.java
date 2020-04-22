package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Announcement {

    private Long id;
    private Long ownerID;
    private List<Dish> dishes;
    private String date;

    public Announcement() {
        this.dishes = new ArrayList<>();
    }
    public Long getID() { return id; };
    public String getDate() {
        return date.toString();
    }
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
    public void setOwnerID(Long ownerID) { this.ownerID = ownerID; }
    public Long getOwnerID() { return ownerID; }
    public void setID(Long id) { this.id = id; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("ownerId=" + ownerID)
                .add("dishes=" + dishes)
                .add("date=" + date)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return getID().equals(that.getID()) &&
                getDishes().equals(that.getDishes()) &&
                getOwnerID().equals(that.getOwnerID()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getOwnerID(), getDishes(), getDate());
    }

    public static class Builder {
        private Announcement entity;

        public Builder() {
            entity = new Announcement();
        }

        public Builder withOwnerID(Long ownerID) {
            entity.setOwnerID(ownerID);
            return this;
        }

        public Builder withDate(String date) {
            entity.setDate(date);
            return this;
        }

        public Builder withID(Long id) {
            entity.setID(id);
            return this;
        }

        public Announcement build() {
            return entity;
        }
    }
}