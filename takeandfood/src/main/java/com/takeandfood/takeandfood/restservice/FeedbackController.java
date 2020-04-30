package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.DAO.FeedbackDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.business.FeedbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackHandler feedbackHandler;

    @DeleteMapping("/feedback")
    public ResponseEntity<Object> delete(@RequestParam("id") String id) {
        try {
            feedbackHandler.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @PutMapping("/feedback")
//    public ResponseEntity<Object> update(@RequestBody Feedback feedback) {
//        try {
//            feedbackHandler.update(feedback);
//            return ResponseEntity.ok().build();
//        } catch(NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/feedback")
    public ResponseEntity<Feedback> get(@RequestParam("id") String id) {
        try {
            Feedback feedback = feedbackHandler.get(id);
            return ResponseEntity.ok(feedback);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/feedback/restaurant")
    public ResponseEntity<List<Feedback>> allRestaurant(@RequestParam("id") String id) {
        return ResponseEntity.ok(feedbackHandler.getAllForRestaurant(id));
    }

    @GetMapping("/feedback/all")
    public ResponseEntity<List<Feedback>> all() {
        return ResponseEntity.ok(feedbackHandler.getAll());
    }

    @PostMapping("/feedback")
    public ResponseEntity<Object> insert(@RequestBody Feedback feedback) {
        try {
            feedbackHandler.create(feedback);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
