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
        if(feedbackHandler.delete(id) && id > 0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<FeedbackDto> update(@RequestBody FeedbackDto feedback) {
        FeedbackDto feedbackDto = feedbackHandler.update(feedback);
        if(feedbackDto != null) {
            return ResponseEntity.ok(feedbackHandler.update(feedback));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        FeedbackDto feedbackDto = feedbackHandler.get(id);
        if(feedbackDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(feedbackHandler.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackDto>> all(@RequestParam("page") Integer page) {
        if(page > 0) {
            return ResponseEntity.ok(feedbackHandler.getAll(page));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> insert(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto = feedbackHandler.create(feedbackDto);
        if(feedbackDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(feedbackDto);
    }
}
