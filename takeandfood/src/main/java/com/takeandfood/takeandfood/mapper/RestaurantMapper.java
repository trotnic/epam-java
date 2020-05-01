package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dto.RestaurantDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper extends AbstractMapper<Restaurant, RestaurantDto> {


    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantMapper(ModelMapper modelMapper) {
        super(Restaurant.class, RestaurantDto.class);
        this.modelMapper = modelMapper;
    }

    @Override
    Converter<Restaurant, RestaurantDto> toDtoConverter() {
        return MappingContext::getDestination;
    }

    @Override
    Converter<RestaurantDto, Restaurant> toEntityConverter() {
        return MappingContext::getDestination;
    }
}
