package com.takeandfood.takeandfood.beans;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.StringJoiner;

//@Component
public class Feedback {
    private Long id;
    private Long userID;
    private String date;
    private String text;
    private Long restaurantID;

    public Feedback() {}

    public Long getId() {
        return id;
    }
    public Long getUserID() {
        return userID;
    }
    public String getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public Long getRestaurantID() {
        return restaurantID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setRestaurantID(Long restaurantID) {
        this.restaurantID = restaurantID;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Feedback.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userID=" + userID)
                .add("date='" + date + "'")
                .add("text='" + text + "'")
                .add("restaurantID='" + restaurantID + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return getId().equals(feedback.getId()) &&
                getUserID().equals(feedback.getUserID()) &&
                getDate().equals(feedback.getDate()) &&
                getText().equals(feedback.getText()) &&
                getRestaurantID().equals(feedback.restaurantID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserID(), getDate(), getText(), getRestaurantID());
    }

    public static class Builder {
        private Feedback entity;

        public Builder() {
            entity = new Feedback();
        }

        public Builder withText(String text) {
            entity.setText(text);
            return this;
        }

        public Builder withUserID(Long userID) {
            entity.setUserID(userID);
            return this;
        }

        public Builder withDate(String date) {
            entity.setDate(date);
            return this;
        }

        public Builder withRestaurantID(Long restaurantID) {
            entity.setRestaurantID(restaurantID);
            return this;
        }

        public Builder withID(Long id){
            entity.setId(id);
            return this;
        }

        public Feedback build() {
            return entity;
        }
    }
}
