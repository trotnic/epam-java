package com.company.models;

import java.util.*;

public class Announcement extends Model {
    private String id;
    private Restaurant owner;
    private List<Position> positions;
    private Date date;

    public Announcement() {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.positions = new ArrayList<>();
    }

    public Announcement(Restaurant owner) {
        this();
        this.owner = owner;
    }

    public String getId() { return this.id; };

    public Restaurant getOwner() {
        return owner;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Date getDate() {
        return date;
    }

    public void setOwner(Restaurant owner) {
        this.owner = owner;
    }

    public void addPosition(Position position) {
        this.positions.add(position);
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Announcement.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("owner=" + owner)
                .add("positions=" + positions)
                .add("date=" + date)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return getOwner().equals(that.getOwner()) &&
                getPositions().equals(that.getPositions()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
