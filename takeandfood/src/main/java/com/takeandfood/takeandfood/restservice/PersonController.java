package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.service.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonHandler personHandler;

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        personHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody PersonDto person) {
        personHandler.update(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> all() {
        return ResponseEntity.ok(personHandler.getAll());
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        PersonDto person = personHandler.get(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody PersonDto person) {
        personHandler.create(person);
        return ResponseEntity.ok().build();
    }
}
