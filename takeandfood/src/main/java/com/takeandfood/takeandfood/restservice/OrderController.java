package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import com.takeandfood.takeandfood.dto.OrderDto;
import com.takeandfood.takeandfood.service.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {


    private OrderHandler orderHandler;

    @Autowired
    public OrderController(OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        orderHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto order) {
        return ResponseEntity.ok(orderHandler.update(order));
    }

    @GetMapping
    public ResponseEntity<OrderDto> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(orderHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> all(@RequestParam("page") Integer page) {
        return ResponseEntity.ok(orderHandler.getAll(page));
    }

    @PostMapping
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto order) {
        return ResponseEntity.ok(orderHandler.create(order));
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<OrderDto>> getForRestaurant(@RequestParam("id") Long id) {
        return ResponseEntity.ok(orderHandler.getForRestaurant(id));
    }
}
