package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dao.FeedbackDao;
import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.dto.RestaurantDto;
import com.takeandfood.takeandfood.mapper.FeedbackMapper;
import com.takeandfood.takeandfood.mapper.RestaurantMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestaurantHandler {

    private RestaurantDao restaurantDao;
    private FeedbackDao feedbackDao;
    private RestaurantMapper restaurantMapper;
    private FeedbackMapper feedbackMapper;


    @Autowired
    public RestaurantHandler(RestaurantDao restaurantDao,
                             RestaurantMapper mapper,
                             FeedbackMapper feedbackMapper,
                             FeedbackDao feedbackDao) {
        this.restaurantDao = restaurantDao;
        this.restaurantMapper = mapper;
        this.feedbackMapper = feedbackMapper;
        this.feedbackDao = feedbackDao;
    }

    @Transactional
    public boolean delete(Long id) {
        if(restaurantDao.get(id).isPresent()) {
            restaurantDao.delete(id);
            return true;
        }
        return false;
    }

    @Transactional
    public RestaurantDto get(Long id) {
        Optional<Restaurant> restaurant = restaurantDao.get(id);
        return restaurant.map(value -> restaurantMapper.toDto(value)).orElse(null);
    }

    @Transactional
    public List<RestaurantDto> getAll(Integer page) {
        return restaurantDao.getAll(page).stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public RestaurantDto create(RestaurantDto restaurant) {
        try {
            Restaurant rest = restaurantMapper.toEntity(restaurant);
            restaurantDao.create(rest);
            return restaurantMapper.toDto(rest);
        } catch(ObjectNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public RestaurantDto update(RestaurantDto restaurant) {
        if(!restaurantDao.get(restaurant.getId()).isPresent()) {
            return null;
        }
        return restaurantMapper.toDto(restaurantDao.update(restaurantMapper.toEntity(restaurant)));
    }

    @Transactional
    public List<FeedbackDto> getFeedback(Long id) {
        if(!restaurantDao.get(id).isPresent()) {
            return null;
        }
        return feedbackDao.getByRestaurant(id).stream().map(feedbackMapper::toDto).collect(Collectors.toList());
    }
}
