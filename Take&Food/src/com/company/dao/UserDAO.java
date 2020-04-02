package com.company.dao;

import com.company.models.User;

public class UserDAO extends DAO<User, String> {
    private static String path = "data/user.dat";

    @Override
    public String path() {
        return path;
    }

}
