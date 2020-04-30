package com.takeandfood.takeandfood.business;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.DAO.PersonDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Component
public class PersonHandler {

    @Autowired
    private PersonDAO personDAO;

    public void delete(String id) throws InvalidAttributeValueException {
        Pattern pattern = Pattern.compile("\\d+?");
        if(!pattern.matcher(id).matches() || !personDAO.get(id).isPresent()) {
            throw new InvalidAttributeValueException("Bad request parameter");
        }
        personDAO.delete(id);
    }

    public Person get(String id) throws NoEntityException {
        return personDAO.get(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    public List<Person> getAll() {
        return personDAO.getAll();
    }

    public void create(Person person) throws InvalidAttributeValueException {
        if(!personDAO.create(person)) return ;
    }

    public void update(Person person) throws InvalidAttributeValueException {

    }
}
