package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Restaurant extends Model {
    private Long id;
    private Administrator admin;
    private String name;

    public Long getId() {
        return id;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("admin=" + admin)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return getId().equals(that.getId()) &&
                getAdmin().equals(that.getAdmin()) &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdmin(), getName());
    }
}
