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
    public ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurant) {
        return ResponseEntity.ok(restaurantHandler.update(restaurant));
    }

    @GetMapping
    public ResponseEntity<RestaurantDto> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(restaurantHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDto>> all() {
        return ResponseEntity.ok(restaurantHandler.getAll());
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> insert(@RequestBody RestaurantDto restaurant) {
        return ResponseEntity.ok(restaurantHandler.create(restaurant));
    }
}
