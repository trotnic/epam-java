package com.takeandfood.takeandfood.forms;/*
 * @project takeandfood
 * @author vladislav on 4/24/20
 */

import com.takeandfood.takeandfood.beans.Dish;

public class AnnouncementForm {
    private Long id;
    private String date;
    private Long ownerID;
    private Dish[] dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Dish[] getDishes() {
        if(dishes == null) {
            return new Dish[]{};
        }
        return dishes;
    }

    public void setDishes(Dish[] dishes) {
        this.dishes = dishes;
    }
}
