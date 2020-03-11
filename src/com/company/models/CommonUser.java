package com.company.models;

import com.company.collections.SocialStatus;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class CommonUser extends User {
    private SocialStatus status;
    private List<Order> takenOrders;

    public SocialStatus getStatus() {
        return status;
    }

    public List<Order> getTakenOrders() {
        return takenOrders;
    }

    public void setStatus(SocialStatus status) {
        this.status = status;
    }

    public void addTakenOrder(Order order) {
        this.takenOrders.add(order);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonUser.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("takenOrders=" + takenOrders)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommonUser that = (CommonUser) o;
        return getStatus() == that.getStatus() &&
                getTakenOrders().equals(that.getTakenOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStatus(), getTakenOrders());
    }
}
