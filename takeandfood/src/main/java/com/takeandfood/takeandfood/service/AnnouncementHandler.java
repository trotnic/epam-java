package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Order;
import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.dao.OrderDao;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dto.AnnouncementDto;
import com.takeandfood.takeandfood.mapper.AnnouncementMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnouncementHandler {

    private AnnouncementDao announcementDao;
    private AnnouncementMapper mapper;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    public AnnouncementHandler(AnnouncementDao announcementDao, AnnouncementMapper mapper) {
        this.announcementDao = announcementDao;
        this.mapper = mapper;
    }

    @Transactional
    public boolean delete(Long id) {
        if(announcementDao.get(id).isPresent()) {
            orderDao.deleteWithAnnouncement(id);
            announcementDao.delete(id);
            return true;
        }
        return false;
    }

    @Transactional
    public AnnouncementDto get(Long id) {
        Optional<Announcement> announcement = announcementDao.get(id);
        return announcement.map(value -> mapper.toDto(value)).orElse(null);
    }

    @Transactional
    public List<AnnouncementDto> getAll(Integer page) {
        return announcementDao.getAll(page).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementDto create(AnnouncementDto announcement) {
        try {
//            Announcement toCreate = ;
            announcementDao.create(mapper.toEntity(announcement));
            return announcement;
        } catch(ObjectNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public AnnouncementDto update(AnnouncementDto announcement) {
        if(!announcementDao.get(announcement.getId()).isPresent()) {
            return null;
        }
        return mapper.toDto(announcementDao.update(mapper.toEntity(announcement)));
    }

    @Transactional
    public List<AnnouncementDto> getOrderingsByPerson(Long id) {
        if(!personDao.get(id).isPresent()) {
            return null;
        }
        List<Order> orders = orderDao.getForPerson(id);
        List<Announcement> announcements = new ArrayList<>();
        orders.forEach(e -> {
            Announcement announcement = announcementDao.get(e.getAnnouncement().getId()).orElse(null);
            if (!Objects.isNull(announcement)) {
                announcements.add(announcement);
            }
        });
        return announcements.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
