package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.DAO.DishDAO;
import com.takeandfood.takeandfood.DAO.RestaurantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Announcement extends Model {

    @Autowired
    private DishDAO dishDAO;

    private Long id;
    private Long ownerID;
//    private HashSet<Dish> dishes;
    private String date;

    public Long getId() { return id; };
    public Optional<Restaurant> owner() { return (new RestaurantDAO()).get(this.id.toString()); }
    public String getDate() {
        return date.toString();
    }
//    public HashSet<Dish> getDishes() { return dishes; }
//    public void setDishes(HashSet<Dish> dishes) { this.dishes = dishes; }
    public List<Dish> listDishes() { return dishDAO.allRelatedTo(id.toString()); }
    public void setOwnerID(Long ownerID) { this.ownerID = ownerID; }
    public Long getOwnerID() { return ownerID; }
    public void setId(Long id) { this.id = id; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("ownerID=" + ownerID)
//                .add("positions=" + dishes)
                .add("date=" + date)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return getId().equals(that.getId()) &&
                getOwnerID().equals(that.getOwnerID()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOwnerID(), getDate());
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

        public Builder withId(Long id) {
            entity.setId(id);
            return this;
        }

        public Announcement build() {
            return entity;
        }
    }
}