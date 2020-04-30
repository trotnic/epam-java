package com.takeandfood.takeandfood.forms;/*
 * @project takeandfood
 * @author vladislav on 4/24/20
 */

import com.takeandfood.takeandfood.beans.Person;

public class RestaurantForm {
    private Long id;
    private String name;
    private Long[] administrators;

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

    public Long[] getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Long[] administrators) {
        this.administrators = administrators;
    }
}
