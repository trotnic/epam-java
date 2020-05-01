package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.DAO.RestaurantDAO;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dto.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    private final ModelMapper modelMapper;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public PersonMapper(ModelMapper modelMapper, RestaurantDAO restaurantDAO) {
        super(Person.class, PersonDto.class);
        this.modelMapper = modelMapper;
        this.restaurantDAO = restaurantDAO;
    }

    @PostConstruct
    public void setupMapper() {
        
    }
}
