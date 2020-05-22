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
        if(orderHandler.delete(id) && id > 0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto order) {
        if(order.getId() > 0) {
            OrderDto orderDto = orderHandler.update(order);
            if(orderDto != null) {
                return ResponseEntity.ok(orderDto);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<OrderDto> get(@RequestParam("id") Long id) {
        OrderDto orderDto = orderHandler.get(id);
        if(orderDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> all(@RequestParam("page") Integer page) {
        if(page > 0) {
            return ResponseEntity.ok(orderHandler.getAll(page));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto order) {
        order = orderHandler.create(order);
        if(order == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderHandler.create(order));

    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<OrderDto>> getForRestaurant(@RequestParam("id") Long id) {
        if(id > 0) {
            List<OrderDto> list = orderHandler.getForRestaurant(id);
            if(list != null) {
                return ResponseEntity.ok(list);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
