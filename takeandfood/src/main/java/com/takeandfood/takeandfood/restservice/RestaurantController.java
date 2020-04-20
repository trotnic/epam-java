package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.DAO.RestaurantDAO;
import com.takeandfood.takeandfood.beans.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @RequestMapping("/restaurant/delete")
    public void delete(@RequestParam("id") String id) {
        restaurantDAO.delete(id);
    }

    @RequestMapping("/restaurant/update")
    public void update(@ModelAttribute Restaurant restaurant) {
        restaurantDAO.update(restaurant);
    }

    @RequestMapping("/restaurant")
    public Optional<Restaurant> get(@RequestParam("id") String id) {
        return restaurantDAO.get(id);
    }

    @RequestMapping("/restaurant/all")
    public List<Restaurant> all() {
        return restaurantDAO.getAll();
    }

    @RequestMapping("/restaurant/insert")
    public void insert(@ModelAttribute Restaurant restaurant) {
        restaurantDAO.create(restaurant);
    }
}
