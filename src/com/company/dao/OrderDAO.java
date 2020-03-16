package com.company.dao;

import com.company.models.Order;

public class OrderDAO extends DAO<Order, String> {
    private static String path = "data/order.dat";

    @Override
    public String path() {
        return path;
    }
}
