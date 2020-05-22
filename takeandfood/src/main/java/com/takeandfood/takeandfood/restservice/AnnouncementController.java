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
import java.util.Objects;

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
        if (announcementHandler.delete(id) && id > 0) {
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<AnnouncementDto> update(@RequestBody AnnouncementDto announcement) {
        if(announcement.getId() > 0) {
            AnnouncementDto announcementDto = announcementHandler.update(announcement);
            if(announcementDto != null) {
                return ResponseEntity.ok(announcementDto);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementDto>> all(@RequestParam("page") Integer page) {
        if(page > 0) {
            return ResponseEntity.ok(announcementHandler.getAll(page));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<AnnouncementDto> get(@RequestParam("id") Long id) {
        AnnouncementDto announcementDto = announcementHandler.get(id);
        if (announcementDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(announcementHandler.get(id));
    }

    @PostMapping
    public ResponseEntity<AnnouncementDto> insert(@RequestBody AnnouncementDto announcement) {
        announcement = announcementHandler.create(announcement);
        if(announcement == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(announcement);
    }

    @GetMapping("/person")
    public ResponseEntity<List<AnnouncementDto>> getForPerson(@RequestParam("id") Long id) {
        if(id > 0) {
            List<AnnouncementDto> list = announcementHandler.getOrderingsByPerson(id);
                if(list != null) {
                    return ResponseEntity.ok(list);
                }
        }
        return ResponseEntity.badRequest().build();
    }
}
