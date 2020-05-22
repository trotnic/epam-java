package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Restaurant;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.dto.PersonDto;
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
        if(id > 0 && restaurantHandler.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurant) {
        RestaurantDto restaurantDto = restaurantHandler.update(restaurant);
        if(restaurantDto != null) {
            return ResponseEntity.ok(restaurantHandler.update(restaurant));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<RestaurantDto> get(@RequestParam("id") Long id) {
        RestaurantDto restaurantDto = restaurantHandler.get(id);
        if(restaurantDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(restaurantHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDto>> all(@RequestParam("page") Integer page) {
        if(page > 0) {
            return ResponseEntity.ok(restaurantHandler.getAll(page));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> insert(@RequestBody RestaurantDto restaurantDto) {
        restaurantDto = restaurantHandler.create(restaurantDto);
        if(restaurantDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(restaurantHandler.create(restaurantDto));
    }

    @GetMapping("/feedback")
    public ResponseEntity<List<FeedbackDto>> getFeedback(@RequestParam("id") Long id) {
        List<FeedbackDto> list = restaurantHandler.getFeedback(id);
        if(list == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(list);
    }
}
