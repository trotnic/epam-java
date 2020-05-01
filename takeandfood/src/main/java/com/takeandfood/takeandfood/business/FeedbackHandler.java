package com.takeandfood.takeandfood.business;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.dao.FeedbackDao;
import com.takeandfood.takeandfood.beans.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Component
public class FeedbackHandler {

    @Autowired
    private FeedbackDao feedbackDAO;

    public void delete(String id) throws InvalidAttributeValueException {
        Pattern pattern = Pattern.compile("\\d+?");
        if(!pattern.matcher(id).matches() || !feedbackDAO.get(id).isPresent()) {
            throw new InvalidAttributeValueException("Bad request parameter");
        }
        feedbackDAO.delete(id);
    }

    public Feedback get(String id) throws NoSuchElementException {
        return feedbackDAO.get(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    public List<Feedback> getAll() {
        return feedbackDAO.getAll();
    }

    public void create(Feedback feedback) throws InvalidAttributeValueException{
        Pattern pattern = Pattern.compile("\\s+");
//        if(pattern.matcher(restaurantForm.getName()).matches() ||
//                restaurantForm.getName().equals("")) {
//            throw new InvalidAttributeValueException(restaurantForm.getName());
//        }
        feedbackDAO.create(feedback);
    }

//    public List<Feedback> getAllForRestaurant(String id) {
//        return feedbackDAO.getAllRelatedTo(id);
//    }

//    public void update(Feedback )


}
