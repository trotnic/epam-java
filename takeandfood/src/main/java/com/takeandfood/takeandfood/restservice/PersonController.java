package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.DAO.PersonDAO;
import com.takeandfood.takeandfood.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/delete")
    public void delete(@RequestParam(value="id", defaultValue = "0") String id) {
        personDAO.delete(id);
    }

    @RequestMapping("/update")
    public void update(
            @RequestParam(value="id", defaultValue = "") String id,
            @RequestParam(value = "login", defaultValue = "") String login,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "password", defaultValue = "") String password,
            @RequestParam(value = "role", defaultValue = "") String role,
            @RequestParam(value = "status", defaultValue = "") String status) {
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

    @RequestMapping("/all")
    public List<Person> all() {
        return personDAO.getAll();
    }

    @RequestMapping("/get")
    public Optional<Person> get(@RequestParam(value="id", defaultValue = "0") String id) {
        return personDAO.get(id);
    }

    //TODO recieve params in a body
    @RequestMapping("/insert")
    public void insert(
            @RequestParam(value = "login", defaultValue = "null") String login,
            @RequestParam(value = "email", defaultValue = "null") String email,
            @RequestParam(value = "name", defaultValue = "null") String name,
            @RequestParam(value = "password", defaultValue = "null") String password,
            @RequestParam(value = "role", defaultValue = "0") String role,
            @RequestParam(value = "status", defaultValue = "0") String status) {
        personDAO.create(
                new Person.Builder()
                        .withEmail(email)
                        .withId(0L)
                        .withLogin(login)
                        .withName(name)
                        .withPassword(password)
                        .withRole(Integer.parseInt(role))
                        .withStatus(Integer.parseInt(status))
                        .build()
        );
    }
}
