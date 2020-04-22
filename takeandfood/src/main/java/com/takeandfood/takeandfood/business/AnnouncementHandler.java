package com.takeandfood.takeandfood.business;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.DAO.AnnouncementDAO;
import com.takeandfood.takeandfood.DAO.FeedbackDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Feedback;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class AnnouncementHandler {

    @Autowired
    private AnnouncementDAO announcementDAO;

    public void delete(String id) throws InvalidAttributeValueException {
        Pattern pattern = Pattern.compile("\\d+?");
        if(!pattern.matcher(id).matches() || !announcementDAO.get(id).isPresent()) {
            throw new InvalidAttributeValueException("Bad request parameter");
        }
        announcementDAO.delete(id);
    }

    public Announcement get(String id) throws NoEntityException {
        return announcementDAO.get(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    public List<Announcement> getAll() {
        return announcementDAO.getAll();
    }

    public void create(Announcement announcement) throws InvalidAttributeValueException{
        Pattern pattern = Pattern.compile("\\s+");
//        if(pattern.matcher(restaurantForm.getName()).matches() ||
//                restaurantForm.getName().equals("")) {
//            throw new InvalidAttributeValueException(restaurantForm.getName());
//        }
        announcementDAO.create(announcement);
    }
}
