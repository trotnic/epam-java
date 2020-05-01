package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.service.FeedbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    private FeedbackHandler feedbackHandler;

    @Autowired
    public FeedbackController(FeedbackHandler feedbackHandler) {
        this.feedbackHandler = feedbackHandler;
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        feedbackHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody FeedbackDto feedback) {
        feedbackHandler.update(feedback);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        FeedbackDto feedback = feedbackHandler.get(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> all() {
        return ResponseEntity.ok(feedbackHandler.getAll());
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody FeedbackDto feedbackDto) {
        feedbackHandler.create(feedbackDto);
        return ResponseEntity.ok().build();
    }
}
