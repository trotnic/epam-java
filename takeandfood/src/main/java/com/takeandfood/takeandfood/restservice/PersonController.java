package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.DAO.PersonDAO;
import com.takeandfood.takeandfood.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/person/delete")
    public void delete(@RequestParam(value="id", defaultValue = "0") String id) {
        personDAO.delete(id);
    }


    //TODO recieve params in a body
    @RequestMapping("/person/update")
    public void update(
            @RequestParam(value="id", defaultValue = "0") String id,
            @RequestParam(value = "login", defaultValue = "null") String login,
            @RequestParam(value = "email", defaultValue = "null") String email,
            @RequestParam(value = "name", defaultValue = "null") String name,
            @RequestParam(value = "password", defaultValue = "null") String password,
            @RequestParam(value = "role", defaultValue = "0") String role,
            @RequestParam(value = "status", defaultValue = "0") String status) {
        Person person = new Person.Builder()
                        .withEmail(email)
                        .withId(Long.parseLong(id))
                        .withLogin(login)
                        .withName(name)
                        .withPassword(password)
                        .withRole(Integer.parseInt(role))
                        .withStatus(Integer.parseInt(status))
                        .build();
        personDAO.update(person);
    }

    @RequestMapping("/person/all")
    public List<Person> all() {
        return personDAO.getAll();
    }

    @RequestMapping("/person")
    public Optional<Person> get(@RequestParam(value="id", defaultValue = "0") String id) {
        return personDAO.get(id);
    }

    //TODO recieve params in a body
    @RequestMapping("/person/insert")
    public void insert(@ModelAttribute Person person) {
        personDAO.create(person);
    }
}
