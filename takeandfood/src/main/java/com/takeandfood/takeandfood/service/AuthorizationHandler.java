package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 5/22/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dto.AuthDto;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.mapper.AuthMapper;
import com.takeandfood.takeandfood.mapper.PersonMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class AuthorizationHandler {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AuthMapper authMapper;

    @Transactional
    public PersonDto login(AuthDto authDto) {
        Optional<Person> person = personDao.getByLogin(authDto.getLogin());
        if(person.isPresent() && person.get().getPassword().equals(authDto.getPassword())) {
            return personMapper.toDto(person.get());
        }
        return null;
    }

    @Transactional
    public PersonDto register(AuthDto authDto) {
        if(personDao.getByLogin(authDto.getLogin()).isPresent() ||
                !Pattern.compile("[a-zA-Z]*").matcher(authDto.getPassword()).matches()) {
            return null;
        }
        try {
            Person toCreate = authMapper.toDto(authDto);
            personDao.create(toCreate);
            return personMapper.toDto(toCreate);
        } catch(NullPointerException e) {
            return null;
        }
    }
}
