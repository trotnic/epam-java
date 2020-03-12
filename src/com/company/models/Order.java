package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Order extends Model {
    private Long id;
    private Announcement orderItem;

    public Announcement getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Announcement orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderItem=" + orderItem)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                getOrderItem().equals(order.getOrderItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
