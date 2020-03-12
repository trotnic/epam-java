package com.company.dao;

import com.company.models.Order;

public class OrderDAO extends DAO<Order, Long> {
    private static String path = "data/order.dat";

    @Override
    public String path() {
        return path;
    }
}
