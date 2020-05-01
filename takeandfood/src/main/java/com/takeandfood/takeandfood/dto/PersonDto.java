package com.takeandfood.takeandfood.dto;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import java.util.StringJoiner;

public class PersonDto {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Long role;
    private Long restaurantId;
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("restaurantId=" + restaurantId)
                .add("status=" + status)
                .toString();
    }
}
