package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "RESTAURANT_ID")
    private List<Person> administrators;

    public Restaurant() {
        this.administrators = new ArrayList<>();
    }

//    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    public List<Person> getAdministrators() { return administrators; }
    public String getName() {
        return name;
    }
    public void addAdmin(Person admin) {
        administrators.add(admin);
    }
    public void removeAdmin(Person admin) { administrators.remove(admin); }
    public void setName(String name) { this.name = name; }
    public void setAdministrators(List<Person> administrators) { this.administrators = administrators; }

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

//    public static class Builder {
//        private Restaurant entity;
//
//        public Builder() {
//            entity = new Restaurant();
//        }
//
//        public Builder withName(String name) {
//            entity.setName(name);
//            return this;
//        }
//
//        public Builder withId(Long id) {
//            entity.setId(id);
//            return this;
//        }
//
//        public Builder withAdministrators(List<Person> administrators) {
//            entity.setAdministrators(administrators);
//            return this;
//        }
//
//        public Restaurant build() {
//            return entity;
//        }
//
//    }
}