package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Dish extends Model {
    private String name;
    private String amount;


    public Dish() {}

    public Dish(String name, String amount) {
        this();
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public String getAmount() {
        return amount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dish.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("amount='" + amount + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getName().equals(dish.getName()) &&
                getAmount().equals(dish.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAmount());
    }
}
