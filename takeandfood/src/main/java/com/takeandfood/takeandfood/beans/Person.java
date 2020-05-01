package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.collections.Roles;
import com.takeandfood.takeandfood.collections.SocialStatuses;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//@Component
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private Long role;

    @ManyToOne
    @Column(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Column(name = "STATUS")
    private Long status;

    public Person() {}

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
        return Roles.values()[Math.toIntExact(role)];
    }

    public Restaurant getRestaurant() { return restaurant; }

    public SocialStatuses getStatus() { return SocialStatuses.values()[Math.toIntExact(status)]; }

    public void setId(String id) { this.id = Long.parseLong(id); }
    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Long role) { this.role = role; }
    public void setStatus(Long status) { this.status = status; }
    public void setPassword(String password) { this.password = password; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getLogin(), person.getLogin()) &&
                Objects.equals(getPassword(), person.getPassword()) &&
                Objects.equals(getEmail(), person.getEmail()) &&
                getRole() == person.getRole() &&
                Objects.equals(getRestaurant(), person.getRestaurant()) &&
                getStatus() == person.getStatus();
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

//    public static class Builder {
//        private Person entity;
//
//        public Builder() {
//            entity = new Person();
//        }
//
//        public Builder withName(String name) {
//            entity.setName(name);
//            return this;
//        }
//
//        public Builder withLogin(String login) {
//            entity.setLogin(login);
//            return this;
//        }
//
//        public Builder withPassword(String password) {
//            entity.setPassword(password);
//            return this;
//        }
//
//        public Builder withId(Long id) {
//            entity.setId(id.toString());
//            return this;
//        }
//
//        public Builder withEmail(String email) {
//            entity.setEmail(email);
//            return this;
//        }
//
//        public Builder withRole(Number value) {
//            entity.setRole(value.toString());
//            return this;
//        }
//
//        public Builder withStatus(Number value) {
//            entity.setStatus(value.toString());
//            return this;
//        }
//
//        public Builder withRestaurantID(Long restaurantID) {
//            entity.setRestaurantID(restaurantID);
//            return this;
//        }
//
//        public Person build() {
//            return entity;
//        }
//    }
}