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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Person> administrators;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Announcement> announcements;


    public Long getId() { return id; }
    public List<Person> getAdministrators() { return administrators; }
    public String getName() {
        return name;
    }
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void addAdmin(Person admin) {
        administrators.add(admin);
    }
    public void removeAdmin(Person admin) { administrators.remove(admin); }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAdministrators(List<Person> administrators) { this.administrators = administrators; }
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("administrators=" + administrators)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAdministrators(), that.getAdministrators()) &&
                Objects.equals(getAnnouncements(), that.getAnnouncements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAdministrators(), getAnnouncements());
    }
}