package com.takeandfood.takeandfood.service;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.dao.FeedbackDao;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import com.takeandfood.takeandfood.mapper.FeedbackMapper;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean delete(Long id) {
        if(feedbackDao.get(id).isPresent()) {
            feedbackDao.delete(id);
            return true;
        }
        return false;
    }

    @Transactional
    public FeedbackDto get(Long id) {
        Optional<Feedback> feedback = feedbackDao.get(id);
        return feedback.map(value -> mapper.toDto(value)).orElse(null);
    }

    @Transactional
    public List<FeedbackDto> getAll(Integer page) {
        return feedbackDao.getAll(page).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public FeedbackDto create(FeedbackDto feedback) {
        try {
            Feedback toCreate = mapper.toEntity(feedback);
            feedbackDao.create(toCreate);
            return mapper.toDto(toCreate);
        } catch(ObjectNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public FeedbackDto update(FeedbackDto feedback) {
        if(!feedbackDao.get(feedback.getId()).isPresent()) {
            return null;
        }
        return mapper.toDto(feedbackDao.update(mapper.toEntity(feedback)));
    }
}
