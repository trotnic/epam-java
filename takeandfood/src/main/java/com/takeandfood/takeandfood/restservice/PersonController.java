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
        if (id > 0 && personHandler.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto person) {
        PersonDto personDto = personHandler.update(person);
        if(personDto != null) {
            return ResponseEntity.ok(personHandler.update(person));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> all(@RequestParam("page") Integer page) {
        return ResponseEntity.ok(personHandler.getAll(page));
    }

    @GetMapping
    public ResponseEntity<PersonDto> get(@RequestParam("id") Long id) {
        PersonDto personDto = personHandler.get(id);
        if(personDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personHandler.get(id));
    }

    @PostMapping
    public ResponseEntity<PersonDto> insert(@RequestBody PersonDto personDto) {
        personDto = personHandler.create(personDto);
        if(personDto == null) {
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personDto);

    }
}
