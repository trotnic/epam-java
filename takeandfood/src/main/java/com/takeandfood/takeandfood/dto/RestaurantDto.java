package com.takeandfood.takeandfood.dto;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import java.util.List;

public class RestaurantDto {
    private Long id;
    private String name;
    private List<PersonDto> administrators;
    private List<AnnouncementDto> announcements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDto> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<PersonDto> administrators) {
        this.administrators = administrators;
    }

    public List<AnnouncementDto> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementDto> announcements) {
        this.announcements = announcements;
    }
}


