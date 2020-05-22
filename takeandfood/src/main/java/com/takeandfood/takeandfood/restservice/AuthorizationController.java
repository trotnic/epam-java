package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 5/15/20
 */

import com.takeandfood.takeandfood.dto.AuthDto;
import com.takeandfood.takeandfood.dto.PersonDto;
import com.takeandfood.takeandfood.service.AuthorizationHandler;
import com.takeandfood.takeandfood.service.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationHandler authorizationHandler;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthDto authDto) {
        PersonDto person = authorizationHandler.login(authDto);
        if(person == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody AuthDto authDto) {
        PersonDto person = authorizationHandler.register(authDto);
        if(person == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(person);
    }

}
