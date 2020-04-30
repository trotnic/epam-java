package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.business.RestaurantHandler;
import com.takeandfood.takeandfood.forms.RestaurantForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantHandler restaurantHandler;

    @DeleteMapping("/restaurant")
    public ResponseEntity<Object> delete(@RequestParam("id") String id) {
        try {
            restaurantHandler.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/restaurant")
    public ResponseEntity<Object> update(@RequestBody Restaurant restaurant) {
        try {
            restaurantHandler.update(restaurant);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/restaurant")
    public ResponseEntity<Object> get(@RequestParam("id") String id) {
        try {
            Restaurant restaurant = restaurantHandler.get(id);
            return ResponseEntity.ok(restaurant);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/restaurant/all")
    public ResponseEntity<List<Restaurant>> all() {
        return ResponseEntity.ok(restaurantHandler.getAll());
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Object> insert(@RequestBody RestaurantForm restaurantForm) {
        try {
            restaurantHandler.create(restaurantForm);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
