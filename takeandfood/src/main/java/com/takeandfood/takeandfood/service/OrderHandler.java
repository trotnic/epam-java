package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Order;
import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.dao.OrderDao;
import com.takeandfood.takeandfood.dto.OrderDto;
import com.takeandfood.takeandfood.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderHandler {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AnnouncementDao announcementDao;

    @Transactional
    public void delete(Long id) {
        Order order = orderDao.get(id).orElse(null);
        Announcement announcement = order.getAnnouncement();
        announcement.setStatus(0L);
        announcementDao.update(announcement);
        this.orderDao.delete(id);
    }

    @Transactional
    public OrderDto get(Long id) {
        return orderMapper.toDto(orderDao.get(id).orElse(null));
    }

    @Transactional
    public List<OrderDto> getAll(Integer page) {
        return orderDao.getAll(page).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public OrderDto create(OrderDto order) {
        Order order1 = orderMapper.toEntity(order);
        Announcement announcement = announcementDao.get(order.getAnnouncementId()).orElse(null);
        // Validate above if id exists
        if (Objects.isNull(announcement)) {
            return order;
        }
        announcement.setStatus(1L);
        announcementDao.update(announcement);
        orderDao.create(order1);
        return orderMapper.toDto(order1);
    }

    @Transactional
    public OrderDto update(OrderDto order) {

        if(order.getStatus() == 1) {
            Announcement announcement = announcementDao.get(order.getAnnouncementId()).orElse(null);
            announcement.setStatus(2L);
            announcementDao.update(announcement);
        }

        return orderMapper.toDto(orderDao.update(orderMapper.toEntity(order)));
    }

    @Transactional
    public List<OrderDto> getForRestaurant(Long id) {
        return orderDao.getForRestaurant(id).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
