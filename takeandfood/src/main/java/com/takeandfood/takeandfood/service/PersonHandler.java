package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.mapper.PersonMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonHandler {

    private PersonDao personDao;
    private PersonMapper mapper;

    @Autowired
    public PersonHandler(PersonDao personDao, PersonMapper mapper) {
        this.personDao = personDao;
        this.mapper = mapper;
    }

    @Transactional
    public boolean delete(Long id) {
        if(personDao.get(id).isPresent()) {
            personDao.delete(id);
            return true;
        }
        return false;
    }

    @Transactional
    public PersonDto get(Long id) {
        Optional<Person> person = personDao.get(id);
        return person.map(value -> mapper.toDto(value)).orElse(null);
    }

    @Transactional
    public List<PersonDto> getAll(Integer page) {
        return personDao.getAll(page).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PersonDto create(PersonDto person) {
        try {
            Person toCreate = mapper.toEntity(person);
            personDao.create(toCreate);
            return mapper.toDto(toCreate);
        } catch(ObjectNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public PersonDto update(PersonDto person) {
        if(!personDao.get(person.getId()).isPresent()) {
            return null;
        }
        return mapper.toDto(personDao.update(mapper.toEntity(person)));
    }

    @Transactional
    public PersonDto getByLogin(String login) {
        Optional<Person> person = personDao.getByLogin(login);
        return person.map(value -> mapper.toDto(value)).orElse(null);
    }
}
