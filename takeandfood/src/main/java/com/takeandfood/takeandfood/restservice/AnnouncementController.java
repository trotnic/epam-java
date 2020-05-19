package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

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
        return ResponseEntity.ok(announcementHandler.update(announcement));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementDto>> all(@RequestParam("page") Integer page) {
        return ResponseEntity.ok(announcementHandler.getAll(page));
    }

    @GetMapping
    public ResponseEntity<AnnouncementDto> get(@RequestParam("id") Long id) {
        return ResponseEntity.ok(announcementHandler.get(id));
    }

    @PostMapping
    public ResponseEntity<AnnouncementDto> insert(@RequestBody AnnouncementDto announcement) {
        System.out.println(announcement);
        return ResponseEntity.ok(announcementHandler.create(announcement));
    }

    @GetMapping("/person")
    public ResponseEntity<List<AnnouncementDto>> getForPerson(@RequestParam("id") Long id) {
        return ResponseEntity.ok(announcementHandler.getOrderedByPerson(id));
    }
}
