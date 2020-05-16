package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.*;

@Entity
@Audited
@Table(name = "ANNOUNCEMENT")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    @Column(name = "DATE")
    private Date date;

    @Column(name = "STATUS")
    private Long status;

    public Announcement(){}

    public Long getId() { return id; };
    public Date getDate() {
        return date;
    }
    public List<Dish> getDishes() { return dishes; }
    public Restaurant getRestaurant() { return restaurant; }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.setAnnouncement(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("restaurant=" + restaurant)
                .add("dishes=" + dishes)
                .add("date=" + date)
                .add("status=" + status)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRestaurant(), that.getRestaurant()) &&
                Objects.equals(getDishes(), that.getDishes()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRestaurant(), getDishes(), getDate(), getStatus());
    }
}