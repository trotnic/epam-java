package com.company.models;

import com.company.collections.Roles;
import com.company.collections.SocialStatus;

import java.util.*;

public class User extends Model {
    private String id;
    private String name;
    private String login;
    private String password;
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
        return id;
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
    public String getPassword() { return password; }
    public Roles getRole() {
        return role;
    }
    public List<Announcement> getOrders() {
        return orderList;
    }
    public SocialStatus getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Roles role) { this.role = role; }
    public void setStatus(SocialStatus status) { this.status = status; }
    public void setPassword(String password) { this.password = password; }
    public void addOrder(Announcement order) {
        orderList.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole() &&
                Objects.equals(orderList, user.orderList) &&
                getStatus() == user.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword(), getEmail(), getRole(), orderList, getStatus());
    }
}
