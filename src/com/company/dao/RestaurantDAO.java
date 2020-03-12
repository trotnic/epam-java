package com.company.dao;

import com.company.models.Restaurant;

public class RestaurantDAO extends DAO<Restaurant, Long> {

    private static String path = "data/restaurant.dat";

    @Override
    public String path() {
        return path;
    }
}
