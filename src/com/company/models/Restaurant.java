package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Restaurant extends Model {
    private String id;
    private String name;
    private Administrator admin;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
    }

    public Restaurant(final String name, final Administrator admin) {
        this();
        this.name = name;
        this.admin = admin;
    }

    public String getId() {
        return this.id;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
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
        return Objects.hash(getId());
    }
}
