package com.takeandfood.takeandfood.dto;/*
 * @project takeandfood
 * @author vladislav on 5/15/20
 */

public class AuthDto {
    private String login;
    private String password;
    private Long status;
    private Long role;

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
}
