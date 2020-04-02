package com.company.models;

import com.company.collections.Roles;
import com.company.collections.SocialStatus;

import java.util.*;

public class User extends Model {
    private String id;
    private String name;
    private String login;
    private String email;
    private Roles role;
    private List<Announcement> orderList;
    private SocialStatus status;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.orderList = new ArrayList<>();
        this.role = Roles.COMMON;
        this.status = SocialStatus.UNDEFINED;
    }

    public User(String name, String login, String email) {
        this();
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Roles role() {
        return this.role;
    }

    public List<Announcement> getOrders() {
        return this.orderList;
    }

    public SocialStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void addOrder(Announcement order) {
        this.orderList.add(order);
    }

    public void setStatus(SocialStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
                .add("role='" + role + "'")
                .add("status='" + status + "'")
                .add("orderList=" + orderList)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getName().equals(user.getName()) &&
                getLogin().equals(user.getLogin()) &&
                getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
