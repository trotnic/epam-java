package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.service.FeedbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FeedbackDto> update(@RequestBody FeedbackDto feedback) {
        return ResponseEntity.ok(feedbackHandler.update(feedback));
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(feedbackHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackDto>> all() {
        return ResponseEntity.ok(feedbackHandler.getAll());
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> insert(@RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackHandler.create(feedbackDto));
    }
}
