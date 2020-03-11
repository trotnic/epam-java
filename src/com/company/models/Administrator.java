package com.company.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Administrator extends User {
    private Restaurant ownership;

    public Restaurant getOwnership() {
        return ownership;
    }

    public void setOwnership(Restaurant ownership) {
        this.ownership = ownership;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Administrator.class.getSimpleName() + "[", "]")
                .add("ownership=" + ownership)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(getOwnership(), that.getOwnership());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnership());
    }
}
