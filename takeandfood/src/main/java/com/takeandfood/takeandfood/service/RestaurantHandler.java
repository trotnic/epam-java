package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dto.RestaurantDto;
import com.takeandfood.takeandfood.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class RestaurantHandler {

    private RestaurantDao restaurantDao;
    private RestaurantMapper mapper;

    @Autowired
    public RestaurantHandler(RestaurantDao restaurantDao, RestaurantMapper mapper) {
        this.restaurantDao = restaurantDao;
        this.mapper = mapper;
    }

    @Transactional
    public void delete(Long id) {
        this.restaurantDao.delete(id);
    }

    @Transactional
    public RestaurantDto get(Long id) {
        return mapper.toDto(restaurantDao.get(id).orElse(null));
    }

    @Transactional
    public List<Restaurant> getAll() {
        return restaurantDao.getAll();
    }

    @Transactional
    public void create(RestaurantDto restaurant) {
        restaurantDao.create(mapper.toEntity(restaurant));
    }

    @Transactional
    public void update(RestaurantDto restaurant) {
        restaurantDao.update(mapper.toEntity(restaurant));
    }
}
