package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/22/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dto.AuthDto;
import com.takeandfood.takeandfood.dto.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AuthMapper extends AbstractMapper<AuthDto, Person> {


    private ModelMapper modelMapper;

    private PersonDao personDao;

    @Autowired
    public AuthMapper(ModelMapper modelMapper, PersonDao personDao) {
        super(AuthDto.class, Person.class);
        this.modelMapper = modelMapper;
        this.personDao = personDao;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(AuthDto.class, Person.class);
    }
}
