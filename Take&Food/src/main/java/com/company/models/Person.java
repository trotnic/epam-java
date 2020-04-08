package com.company.models;

import java.util.*;

public class Person extends Model {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
//    private Roles role;
//    private List<Announcement> orderList;
//    private SocialStatus status;

    public Person() {
//        this.id = UUID.randomUUID().toString();
//        this.orderList = new ArrayList<>();
//        this.role = Roles.COMMON;
//        this.status = SocialStatus.UNDEFINED;
    }

    public Person(String name, String login, String email) {
        this();
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Long getId() {
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
//    public Roles getRole() {
//        return role;
//    }
//    public List<Announcement> getOrders() {
//        return orderList;
//    }
//    public SocialStatus getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setEmail(String email) { this.email = email; }
//    public void setRole(Roles role) { this.role = role; }
//    public void setStatus(SocialStatus status) { this.status = status; }
    public void setPassword(String password) { this.password = password; }
//    public void addOrder(Announcement order) {
//        orderList.add(order);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getLogin(), person.getLogin()) &&
                Objects.equals(getPassword(), person.getPassword()) &&
                Objects.equals(getEmail(), person.getEmail());
//                getRole() == user.getRole() &&
//                Objects.equals(orderList, user.orderList) &&
//                getStatus() == user.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword(), getEmail());
    }
}
