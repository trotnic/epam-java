package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.collections.Roles;
import com.takeandfood.takeandfood.collections.SocialStatuses;

import java.util.*;

public class Person extends Model {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Roles role;
    //    private List<Announcement> orderList;
    private SocialStatuses status;

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
    public Roles getRole() {
        return role;
    }
    //    public List<Announcement> getOrders() {
//        return orderList;
//    }
    public SocialStatuses getStatus() { return status; }

    public void setId(String id) { this.id = Long.parseLong(id); }
    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = Roles.values()[Integer.parseInt(role)]; }
    public void setStatus(String status) { this.status = SocialStatuses.values()[Integer.parseInt(status)]; }
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
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("status=" + status)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword(), getEmail());
    }

    public static class Builder {
        private Person entity;

        public Builder() {
            entity = new Person();
        }

        public Builder withName(String name) {
            entity.setName(name);
            return this;
        }

        public Builder withLogin(String login) {
            entity.setLogin(login);
            return this;
        }

        public Builder withPassword(String password) {
            entity.setPassword(password);
            return this;
        }

        public Builder withId(Long id) {
            entity.setId(id.toString());
            return this;
        }

        public Builder withEmail(String email) {
            entity.setEmail(email);
            return this;
        }

        public Builder withRole(Number value) {
            entity.setRole(value.toString());
            return this;
        }

        public Builder withStatus(Number value) {
            entity.setStatus(value.toString());
            return this;
        }

        public Person build() {
            return entity;
        }


    }
}