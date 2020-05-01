package com.takeandfood.takeandfood.dto;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import java.text.SimpleDateFormat;

public class FeedbackDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private Long id;
    private Long personId;
    private Long restaurantId;
    private String text;
    private String date;

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
