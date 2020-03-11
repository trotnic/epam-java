package com.company.dao;

import com.company.models.CommonUser;

public class CommonUserDAO extends DAO<CommonUser, String> {
    private static String path = "data/commonuser.dat";

    @Override
    public String path() {
        return path;
    }
}
