package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;


@Component
public class Dish {
    private Long id;
    private Long announcementId;
    private String name;
    private Long amount;

    public Dish() {}

    public Dish(String name, Long amount) {
        this();
        this.name = name;
        this.amount = amount;
    }

    public Long getID() { return id; }
    public Long getAnnouncementID() { return announcementId; }
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
    public void setID(Long id) { this.id = id; }
    public void setAnnouncementID(Long announcementId) { this.announcementId = announcementId; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("announcementID'" + announcementId + "'")
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

    public static class Builder {
        private Dish entity;

        public Builder() {
            entity = new Dish();
        }

        public Builder withName(String name) {
            entity.setName(name);
            return this;
        }

        public Builder withAmount(Long amount) {
            entity.setAmount(amount);
            return this;
        }

        public Builder withId(Long id) {
            entity.setID(id);
            return this;
        }

        public Builder withAnnouncement(Long announcement) {
            entity.setAnnouncementID(announcement);
            return this;
        }

        public Dish build() {
            return entity;
        }
    }
}