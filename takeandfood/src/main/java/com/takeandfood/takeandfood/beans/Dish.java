package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.util.Objects;
import java.util.StringJoiner;

public class Dish extends Model {
    private Long id;
    private Long announcementId;
    private String name;
    private String amount;


    public Dish() {}

    public Dish(String name, String amount) {
        this();
        this.name = name;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public Long getAnnouncementId() { return announcementId; }
    public String getName() {
        return name;
    }
    public String getAmount() {
        return amount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public void setId(Long id) { this.id = id; }
    public void setAnnouncementId(Long announcementId) { this.announcementId = announcementId; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
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

        public Builder withAmount(String amount) {
            entity.setAmount(amount);
            return this;
        }

        public Builder withId(Long id) {
            entity.setId(id);
            return this;
        }

        public Builder withAnnouncement(Long announcement) {
            entity.setAnnouncementId(announcement);
            return this;
        }

        public Dish build() {
            return entity;
        }
    }
}