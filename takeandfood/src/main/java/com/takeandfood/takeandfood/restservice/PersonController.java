package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.business.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonHandler personHandler;

    @DeleteMapping("/person")
    public ResponseEntity<Object> delete(@RequestParam(value="id") String id) {
        try {
            personHandler.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    //TODO recieve params in a body
//    @RequestMapping("/person/update")
//    public void update(
//            @RequestParam(value="id", defaultValue = "0") String id,
//            @RequestParam(value = "login", defaultValue = "null") String login,
//            @RequestParam(value = "email", defaultValue = "null") String email,
//            @RequestParam(value = "name", defaultValue = "null") String name,
//            @RequestParam(value = "password", defaultValue = "null") String password,
//            @RequestParam(value = "role", defaultValue = "0") String role,
//            @RequestParam(value = "status", defaultValue = "0") String status) {
//        Person person = new Person.Builder()
//                        .withEmail(email)
//                        .withId(Long.parseLong(id))
//                        .withLogin(login)
//                        .withName(name)
//                        .withPassword(password)
//                        .withRole(Integer.parseInt(role))
//                        .withStatus(Integer.parseInt(status))
//                        .build();
//        personDAO.update(person);
//    }
//
    @GetMapping("/person/all")
    public ResponseEntity<List<Person>> all() {
        return ResponseEntity.ok(personHandler.getAll());
    }

    @RequestMapping("/person")
    public ResponseEntity<Object> get(@RequestParam(value="id", defaultValue = "0") String id) {
        try {
            Person person = personHandler.get(id);
            return ResponseEntity.ok(person);
        } catch(NoEntityException e) {
            return ResponseEntity.notFound().build();
        }
    }
//

    @RequestMapping("/person/insert")
    public ResponseEntity<Object> insert(@RequestBody Person person) {
        try {
            personHandler.create(person);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
