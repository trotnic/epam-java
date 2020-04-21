package com.takeandfood.takeandfood.restservice;/*
 * @project takeandfood
 * @author vladislav on 4/21/20
 */

import com.takeandfood.takeandfood.DAO.FeedbackDAO;
import com.takeandfood.takeandfood.beans.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @RequestMapping("/feedback/delete")
    public void delete(@RequestParam("id") String id) {
        feedbackDAO.delete(id);
    }

    @RequestMapping("/feedback/update")
    public void update(@ModelAttribute Feedback feedback) {
        feedbackDAO.update(feedback);
    }

    @RequestMapping("/feedback")
    public Optional<Feedback> get(@RequestParam("id") String id) {
        return feedbackDAO.get(id);
    }

    @RequestMapping("/feedback/all")
    public List<Feedback> all() { return feedbackDAO.getAll(); }

    @RequestMapping("/feedback/insert")
    public void insert(@ModelAttribute Feedback feedback) {
        feedbackDAO.create(feedback);
    }
}
