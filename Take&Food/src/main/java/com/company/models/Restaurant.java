package com.company.models;

import java.util.*;

public class Restaurant extends Model {
    private String id;
    private String name;
    private HashSet<User> administrators;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
        this.administrators = new HashSet<>();
    }

    public Restaurant(final String name, final User admin) {
        this();
        this.name = name;
        this.administrators.add(admin);
    }

    public String getId() { return id; }
    public HashSet<User> getAdministrators() { return administrators; }
    public String getName() {
        return name;
    }
    public void addAdmin(User admin) {
        administrators.add(admin);
    }
    public void removeAdmin(User admin) { administrators.remove(admin); }
    public void setName(String name) { this.name = name; }

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
}
