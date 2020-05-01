package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void delete(Long id) {
        personDao.delete(id);
    }

    @Transactional
    public PersonDto get(Long id) {
        return mapper.toDto(personDao.get(id).orElse(null));
    }

    @Transactional
    public List<PersonDto> getAll() {
        return personDao.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PersonDto create(PersonDto person) {
        Person pers = mapper.toEntity(person);
        personDao.create(pers);
        return mapper.toDto(pers);
    }

    @Transactional
    public PersonDto update(PersonDto person) {
        return mapper.toDto(personDao.update(mapper.toEntity(person)));
    }
}
