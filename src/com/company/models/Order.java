package com.company.models;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Order extends Model {
    private String id;
    private Announcement orderItem;

    public Order() {
        this.id = UUID.randomUUID().toString();
    }

    public Order(Announcement order) {
        this();
        this.orderItem = order;
    }

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
                .add("id=" + id)
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
        return Objects.hash(getOrderItem(), new Date());
    }

//    public class Builder {
//        public Builder() {
//
//        }
//
//        public Builder setOrderItem(Announcement orderItem) {
//            Order.this.orderItem = orderItem;
//            return this;
//        }
//
//        public Builder setId(Long id) {
//            Order.this.id = id;
//            return this;
//        }
//
//        public Order build() {
//            return Order.this;
//        }
//    }
}
