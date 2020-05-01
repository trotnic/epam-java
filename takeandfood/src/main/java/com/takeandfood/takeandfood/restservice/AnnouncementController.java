package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.dto.AnnouncementDto;
import com.takeandfood.takeandfood.service.AnnouncementHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/announcement")
@RestController
public class AnnouncementController {

    private AnnouncementHandler announcementHandler;

    @Autowired
    public AnnouncementController(AnnouncementHandler announcementHandler) {
        this.announcementHandler = announcementHandler;
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        announcementHandler.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody AnnouncementDto announcement) {
        announcementHandler.update(announcement);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Announcement>> all() {
        return ResponseEntity.ok(announcementHandler.getAll());
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("id") Long id) {
        AnnouncementDto announcement = announcementHandler.get(id);
        return ResponseEntity.ok(announcement);
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody AnnouncementDto announcement) {
        announcementHandler.create(announcement);
        return ResponseEntity.ok().build();
    }

}
