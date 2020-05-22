package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Order;
import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.dao.OrderDao;
import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.dto.OrderDto;
import com.takeandfood.takeandfood.mapper.OrderMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderHandler {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private RestaurantDao restaurantDao;

    @Transactional
    public boolean delete(Long id) {
        Optional<Order> order = orderDao.get(id);
        if(order.isPresent()) {
            Announcement announcement = order.get().getAnnouncement();
            announcement.setStatus(0L);
            announcementDao.update(announcement);
            this.orderDao.delete(id);
            return true;
        }
        return false;
    }

    @Transactional
    public OrderDto get(Long id) {
        Optional<Order> order = orderDao.get(id);
        return order.map(value -> mapper.toDto(value)).orElse(null);
    }

    @Transactional
    public List<OrderDto> getAll(Integer page) {
        return orderDao.getAll(page).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public OrderDto create(OrderDto order) {
        try {
            Order order1 = mapper.toEntity(order);
            Announcement announcement = announcementDao.get(order.getAnnouncementId()).orElse(null);
            // Validate above if id exists
            if (Objects.isNull(announcement)) {
                return order;
            }
            announcement.setStatus(1L);
            announcementDao.update(announcement);
            orderDao.create(order1);
            return mapper.toDto(order1);
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public OrderDto update(OrderDto order) {
        if(!orderDao.get(order.getId()).isPresent()) {
            return null;
        }
        if(order.getStatus() == 1) {
            Announcement announcement = announcementDao.get(order.getAnnouncementId()).orElse(null);
            announcement.setStatus(2L);
            announcementDao.update(announcement);
        }

        return mapper.toDto(orderDao.update(mapper.toEntity(order)));
    }

    @Transactional
    public List<OrderDto> getForRestaurant(Long id) {
        if(!restaurantDao.get(id).isPresent()) {
            return null;
        }
        return orderDao.getForRestaurant(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
