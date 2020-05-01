package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/19/20
 */

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

    private PersonHandler personHandler;

    @Autowired
    public PersonController(PersonHandler personHandler) {
        this.personHandler = personHandler;
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        personHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto person) {
        return ResponseEntity.ok(personHandler.update(person));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> all() {
        return ResponseEntity.ok(personHandler.getAll());
    }

    @GetMapping
    public ResponseEntity<PersonDto> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(personHandler.get(id));
    }

    @PostMapping
    public ResponseEntity<PersonDto> insert(@RequestBody PersonDto person) {
        return ResponseEntity.ok(personHandler.create(person));
    }
}
