package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import javax.persistence.*;
import java.util.*;

//@Component
@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "RESTAURANT_ID")
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToMany
    private List<Dish> dishes;

    @Column(name = "DATE")
    private Date date;

    public Long getId() { return id; };
    public Date getDate() {
        return date;
    }
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setDate(Date date) { this.date = date; }
    public void addDish(Dish dish) { dishes.add(dish); }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("restaurant=" + restaurant)
                .add("dishes=" + dishes)
                .add("date=" + date)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return getId().equals(that.getId()) &&
                getDishes().equals(that.getDishes()) &&
                getRestaurant().equals(that.getRestaurant()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRestaurant(), getDishes(), getDate());
    }

//    public static class Builder {
//        private Announcement entity;
//
//        public Builder() {
//            entity = new Announcement();
//        }
//
//        public Builder withOwnerID(Long ownerID) {
//            entity.setOwnerID(ownerID);
//            return this;
//        }
//
//        public Builder withDate(String date) {
//            entity.setDate(date);
//            return this;
//        }
//
//        public Builder withID(Long id) {
//            entity.setID(id);
//            return this;
//        }
//
//        public Announcement build() {
//            return entity;
//        }
//    }
}