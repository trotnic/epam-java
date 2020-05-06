package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import org.hibernate.envers.Audited;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;


@Entity
@Audited
@Table(name = "DISH")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ANNOUNCEMENT_ID",updatable=false,insertable=false)
    private Announcement announcement;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AMOUNT")
    private Long amount;

    public Dish(){}

    public Long getId() { return id; }
    public Announcement getAnnouncement() { return announcement; }
    public String getName() {
        return name;
    }
    public Long getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    public void setId(Long id) { this.id = id; }
    public void setAnnouncement(Announcement announcement) { this.announcement = announcement; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("announcement='" + announcement + "'")
                .add("amount='" + amount + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getName().equals(dish.getName()) &&
                getAmount().equals(dish.getAmount());
    }

    @Override
    public int hashCode() { return Objects.hash(getName(), getAmount()); }
}