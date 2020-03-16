package com.company.models;

import java.util.StringJoiner;

public class Administrator extends User {
    private Restaurant ownership;

    public Administrator(final String login, final String email, final String name) {
        super(name, login, email);
    }

    public Restaurant getOwnership() {
        return ownership;
    }

    public void setOwnership(Restaurant ownership) {
        this.ownership = ownership;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Administrator.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("ownership=" + ownership)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return
                super.equals(that) &&
                getOwnership().equals(that.getOwnership());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
