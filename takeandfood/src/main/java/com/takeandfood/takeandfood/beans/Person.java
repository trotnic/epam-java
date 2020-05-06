package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.collections.Roles;
import com.takeandfood.takeandfood.collections.SocialStatuses;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.*;

@Entity
@Audited
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Column(name = "STATUS")
    private Long status;

    public Person(){}

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
                .add("restaurant=" + (Objects.isNull(restaurant) ? 0L : restaurant.getId()))
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword(), getEmail());
    }
}