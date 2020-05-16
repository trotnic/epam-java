package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 5/15/20
 */

import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dto.AuthDto;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.service.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private PersonHandler personHandler;

    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestBody AuthDto authDto) {
        PersonDto person = personHandler.getByLogin(authDto.getLogin());
        if(person.getPassword().equals(authDto.getPassword())) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody AuthDto authDto) {
        PersonDto toCreate = new PersonDto();
        toCreate.setStatus(authDto.getStatus());
        toCreate.setLogin(authDto.getLogin());
        toCreate.setPassword(authDto.getPassword());
        toCreate.setRole(authDto.getRole());
        PersonDto person = personHandler.create(toCreate);
        return ResponseEntity.ok(person);
    }

}
