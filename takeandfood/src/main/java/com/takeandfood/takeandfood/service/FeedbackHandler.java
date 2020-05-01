package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.dao.FeedbackDao;
import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackHandler {

    private FeedbackDao feedbackDao;
    private FeedbackMapper mapper;

    @Autowired
    public FeedbackHandler(FeedbackDao feedbackDao, FeedbackMapper mapper) {
        this.feedbackDao = feedbackDao;
        this.mapper = mapper;
    }

    @Transactional
    public void delete(Long id) {
        feedbackDao.delete(id);
    }

    @Transactional
    public FeedbackDto get(Long id) {
        return mapper.toDto(feedbackDao.get(id).orElse(null));
    }

    @Transactional
    public List<Feedback> getAll() {
        return feedbackDao.getAll();
    }

    @Transactional
    public void create(FeedbackDto feedback) {
        feedbackDao.create(mapper.toEntity(feedback));
    }

    @Transactional
    public void update(FeedbackDto feedback) {
        feedbackDao.update(mapper.toEntity(feedback));
    }
}
