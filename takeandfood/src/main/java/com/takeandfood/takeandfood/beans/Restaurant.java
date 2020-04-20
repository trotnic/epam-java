package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import java.util.*;

public class Restaurant extends Model {
    private Long id;
    private String name;
    private HashSet<Person> administrators;

    public Restaurant() {
        this.administrators = new HashSet<>();
    }

    public Restaurant(final String name, final Person admin) {
        this();
        this.name = name;
        this.administrators.add(admin);
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    public HashSet<Person> getAdministrators() { return administrators; }
    public String getName() {
        return name;
    }
    public void addAdmin(Person admin) {
        administrators.add(admin);
    }
    public void removeAdmin(Person admin) { administrators.remove(admin); }
    public void setName(String name) { this.name = name; }
    public void setAdministrators(HashSet<Person> administrators) { this.administrators = administrators; }

    @Override
    public String toString() {
        return new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("admin=" + administrators)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                Objects.equals(getAdministrators(), that.getAdministrators());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAdministrators());
    }

    public static class Builder {
        private Restaurant entity;

        public Builder() {
            entity = new Restaurant();
        }

        public Builder withName(String name) {
            entity.setName(name);
            return this;
        }

        public Builder withId(Long id) {
            entity.setId(id);
            return this;
        }

        public Builder withAdministrators(HashSet<Person> administrators) {
            entity.setAdministrators(administrators);
            return this;
        }

        public Restaurant build() {
            return entity;
        }

    }
}