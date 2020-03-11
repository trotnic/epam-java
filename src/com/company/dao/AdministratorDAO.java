package com.company.dao;

import com.company.models.Administrator;

public class AdministratorDAO extends DAO<Administrator, String> {
    private static String path = "data/administrator.dat";
    @Override
    public String path() {
        return path;
    }
}
