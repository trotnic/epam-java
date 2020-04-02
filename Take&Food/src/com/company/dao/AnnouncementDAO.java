package com.company.dao;

import com.company.models.Announcement;

public class AnnouncementDAO extends DAO<Announcement, String> {
    private static String path = "data/announcement.dat";


    @Override
    public String path() {
        return path;
    }
}
