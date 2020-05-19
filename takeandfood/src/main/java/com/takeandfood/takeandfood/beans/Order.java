package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Audited
@Table(name = "ORDER_ANNOUNCEMENT")
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Person person;


    @Column(name = "STATUS")
    private Long status;

    public Order() {}

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("restaurant=" + restaurant)
                .add("announcement=" + announcement)
                .add("person=" + person)
                .add("status=" + status)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getRestaurant(), order.getRestaurant()) &&
                Objects.equals(getAnnouncement(), order.getAnnouncement()) &&
                Objects.equals(getPerson(), order.getPerson()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRestaurant(), getAnnouncement(), getPerson(), getStatus());
    }
}
