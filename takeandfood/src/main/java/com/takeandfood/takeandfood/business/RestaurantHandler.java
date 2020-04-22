package com.takeandfood.takeandfood.business;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.DAO.RestaurantDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Component
public class RestaurantHandler {

    @Autowired
    private RestaurantDAO restaurantDAO;

    public void delete(String id) throws InvalidAttributeValueException {
        Pattern pattern = Pattern.compile("\\d+?");
        if(!pattern.matcher(id).matches() || !restaurantDAO.get(id).isPresent()) {
            throw new InvalidAttributeValueException("Bad request parameter");
        }
        restaurantDAO.delete(id);
    }

    public Restaurant get(String id) throws NoSuchElementException {
        return restaurantDAO.get(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    public List<Restaurant> getAll() {
        return restaurantDAO.getAll();
    }

    public void create(Restaurant restaurantForm) throws InvalidAttributeValueException{
        Pattern pattern = Pattern.compile("\\s+");
        if(pattern.matcher(restaurantForm.getName()).matches() ||
                restaurantForm.getName().equals("")) {
            throw new InvalidAttributeValueException(restaurantForm.getName());
        }
        restaurantDAO.create(
                new Restaurant.Builder()
                        .withName(restaurantForm.getName())
                        .build()
        );
    }

    public void update(Restaurant restaurant) throws NoSuchElementException {
        Restaurant entity = restaurantDAO.get(restaurant.getId().toString())
                .orElseThrow(() -> new NoSuchElementException(restaurant.getId().toString()));
        if(!restaurant.getName().isEmpty() && !restaurant.getName().equals(entity.getName())) {
            entity.setName(restaurant.getName());
        }
        if(!restaurant.getAdministrators().isEmpty() &&
                !restaurant.getAdministrators().equals(entity.getAdministrators())) {
            entity.setAdministrators(restaurant.getAdministrators());
        }
        restaurantDAO.update(entity);
    }
}
