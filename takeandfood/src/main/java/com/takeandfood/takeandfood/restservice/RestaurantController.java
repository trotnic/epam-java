package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dto.RestaurantDto;
import com.takeandfood.takeandfood.service.RestaurantHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

    private RestaurantHandler restaurantHandler;

    @Autowired
    public RestaurantController(RestaurantHandler restaurantHandler) {
        this.restaurantHandler = restaurantHandler;
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        restaurantHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody RestaurantDto restaurant) {
        restaurantHandler.update(restaurant);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        RestaurantDto restaurant = restaurantHandler.get(id);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> all() {
        return ResponseEntity.ok(restaurantHandler.getAll());
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody RestaurantDto restaurant) {
        restaurantHandler.create(restaurant);
        return ResponseEntity.ok().build();
    }
}
