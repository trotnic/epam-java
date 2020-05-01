package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper extends AbstractMapper<Restaurant, RestaurantDto> {

    @Autowired
    public RestaurantMapper() {
        super(Restaurant.class, RestaurantDto.class);
    }
}
