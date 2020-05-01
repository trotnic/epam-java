package com.takeandfood.takeandfood.dto;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.beans.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AnnouncementDto {
    private Long id;
    private Long restaurantId;

    private String date;
    private List<DishDto> dishes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<DishDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDto> dishes) {
        this.dishes = dishes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AnnouncementDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("restaurantId=" + restaurantId)
                .add("dishes=" + dishes)
                .add("date='" + date + "'")
                .toString();
    }
}
