package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.DAO.AnnouncementDAO;
import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.business.AnnouncementHandler;
import com.takeandfood.takeandfood.forms.AnnouncementForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementHandler announcementHandler;

    @DeleteMapping("/announcement")
    public ResponseEntity<Object> delete(@RequestParam("id") String id) {
        try {
            announcementHandler.delete(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidAttributeValueException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/announcement")
    public ResponseEntity<Object> update(@RequestBody Announcement announcement) {
        try {
            announcementHandler.update(announcement);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/announcement/all")
    public ResponseEntity<List<Announcement>> all() {
        return ResponseEntity.ok(announcementHandler.getAll());
    }

    @GetMapping("/announcement")
    public ResponseEntity<Object> get(@RequestParam("id") String id) {
        try {
            Announcement announcement = announcementHandler.get(id);
            return ResponseEntity.ok(announcement);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/announcement", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Object> insert(@RequestBody AnnouncementForm announcement) {
        try {
            announcementHandler.create(announcement);
            return ResponseEntity.ok().build();
        } catch(InvalidAttributeValueException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
