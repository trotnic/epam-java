package com.company.models;

import java.util.*;

public class Restaurant extends Model {
    private String id;
    private String name;
    private List<User> administrators;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
        this.administrators = new ArrayList<>();
    }

    public Restaurant(final String name, final User admin) {
        this();
        this.name = name;
        this.administrators.add(admin);
    }

    public String getId() {
        return this.id;
    }

    public List<User> getAdmins() {
        return administrators;
    }

    public String getName() {
        return name;
    }

    public void addAdmin(User admin) {
        this.administrators.add(admin);
    }

    public void removeAdmin(User admin) {
        for(int i = 0; i < this.administrators.size(); i++) {
            if(this.administrators.get(i).hashCode() == admin.hashCode()) {
                this.administrators.remove(i);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

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
                getAdmins().equals(that.getAdmins()) &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
