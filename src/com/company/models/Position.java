package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Position extends Model {
    private String name;
    private String amount;

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
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("amount='" + amount + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getName().equals(position.getName()) &&
                getAmount().equals(position.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAmount());
    }
}
