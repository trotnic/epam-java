package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/20/20
 */

import com.takeandfood.takeandfood.DAO.AnnouncementDAO;
import com.takeandfood.takeandfood.beans.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementDAO announcementDAO;

    @DeleteMapping("/announcement")
    public void delete(@RequestParam("id") String id) { announcementDAO.delete(id); }

//    @RequestMapping("/announcement/update")
//    public void update(@ModelAttribute Announcement announcement) { announcementDAO.update(announcement); }

    @GetMapping("/announcement/all")
    public List<Announcement> all() { return announcementDAO.getAll(); }

    @GetMapping("/announcement")
    public Optional<Announcement> get(@RequestParam("id") String id) { return announcementDAO.get(id); }

    @RequestMapping("/announcement/insert")
    public void insert(@ModelAttribute Announcement announcement) { announcementDAO.create(announcement); }

}
