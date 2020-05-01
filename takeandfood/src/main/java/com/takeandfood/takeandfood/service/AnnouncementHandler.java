package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.dto.AnnouncementDto;
import com.takeandfood.takeandfood.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnnouncementHandler {

    private AnnouncementDao announcementDao;
    private AnnouncementMapper mapper;

    @Autowired
    public AnnouncementHandler(AnnouncementDao announcementDao, AnnouncementMapper mapper) {
        this.announcementDao = announcementDao;
        this.mapper = mapper;
    }

    @Transactional
    public void delete(Long id) {
        announcementDao.delete(id);
    }

    @Transactional
    public AnnouncementDto get(Long id) {
        return mapper.toDto(announcementDao.get(id).orElse(null));
    }

    @Transactional
    public List<Announcement> getAll() {
        return announcementDao.getAll();
    }

    @Transactional
    public void create(AnnouncementDto announcement) {
        announcementDao.create(mapper.toEntity(announcement));
    }

    @Transactional
    public void update(AnnouncementDto announcement) throws NoSuchElementException {
        announcementDao.update(mapper.toEntity(announcement));
    }
}
