package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.dto.AnnouncementDto;
import com.takeandfood.takeandfood.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<AnnouncementDto> getAll(Integer page) {
        return announcementDao.getAll(page).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementDto create(AnnouncementDto announcement) {
        announcementDao.create(mapper.toEntity(announcement));
        return announcement;
    }

    @Transactional
    public AnnouncementDto update(AnnouncementDto announcement) {
        return mapper.toDto(announcementDao.update(mapper.toEntity(announcement)));
    }
}
