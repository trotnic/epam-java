package com.company.dao;

import com.company.models.Position;

public class PositionDAO extends DAO<Position, String> {

    private static String path = "data/position.dat";

    @Override
    public String path() {
        return path;
    }
}
