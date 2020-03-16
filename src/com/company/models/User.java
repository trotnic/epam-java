package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class User extends Model {
    private String id;
    private String name;
    private String login;
    private String email;

    public User() {
        this.id = UUID.randomUUID().toString();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
//                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
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
