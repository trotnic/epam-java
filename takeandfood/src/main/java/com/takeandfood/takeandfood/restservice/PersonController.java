package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.DAO.TestDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.beans.Test;
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

    @Autowired
    private TestDAO testDAO;

    @DeleteMapping("/person")
    public ResponseEntity<Object> delete(@RequestParam("id") String id) {
        try {
            personHandler.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/person")
    public ResponseEntity<Object> update(@RequestBody Person person) {
        try {
            personHandler.update(person);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Test>> haha() {
        List<Test> result = testDAO.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/person/all")
    public ResponseEntity<List<Person>> all() {
        return ResponseEntity.ok(personHandler.getAll());
    }

    @GetMapping("/person")
    public ResponseEntity<Object> get(@RequestParam("id") String id) {
        try {
            Person person = personHandler.get(id);
            return ResponseEntity.ok(person);
        } catch(NoEntityException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Object> insert(@RequestBody Person person) {
        try {
            personHandler.create(person);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
