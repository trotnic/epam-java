package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import org.hibernate.envers.Audited;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Audited
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

    @Column(name = "ADDRESS")
    private String address;

    public Restaurant(){}


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAdministrators(), that.getAdministrators()) &&
                Objects.equals(getAnnouncements(), that.getAnnouncements()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAdministrators(), getAnnouncements(), getAddress());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("administrators=" + administrators)
                .add("announcements=" + announcements)
                .add("address='" + address + "'")
                .toString();
    }
}