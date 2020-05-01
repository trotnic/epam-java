package com.takeandfood.takeandfood.business;/*
 * @project takeandfood
 * @author vladislav on 4/22/20
 */

import com.takeandfood.takeandfood.DAO.AnnouncementDAO;
import com.takeandfood.takeandfood.DAO.DishDAO;
import com.takeandfood.takeandfood.DAO.FeedbackDAO;
import com.takeandfood.takeandfood.NoEntityException;
import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Dish;
import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.forms.AnnouncementForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InvalidAttributeValueException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;


@Service
public class AnnouncementHandler {

    @Autowired
    private AnnouncementDAO announcementDAO;


    @Transactional
    public void delete(String id) throws InvalidAttributeValueException {
        Pattern pattern = Pattern.compile("\\d+?");
        if(!pattern.matcher(id).matches() || !announcementDAO.get(id).isPresent()) {
            throw new InvalidAttributeValueException("Bad request parameter");
        }
        announcementDAO.delete(id);
    }

    @Transactional
    public Announcement get(String id) throws NoSuchElementException {
        return announcementDAO.get(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    @Transactional
    public List<Announcement> getAll() {
        return announcementDAO.getAll();
    }

    @Transactional
    public void create(AnnouncementForm announcement) throws InvalidAttributeValueException{

        Pattern pattern = Pattern.compile("\\s+");
//        if(pattern.matcher(restaurantForm.getName()).matches() ||
//                restaurantForm.getName().equals("")) {
//            throw new InvalidAttributeValueException(restaurantForm.getName());
//        }
        Announcement tmp = new Announcement.Builder()
                                .withDate(announcement.getDate())
                                .withOwnerID(announcement.getOwnerID())
                                .build();
        System.out.println(Arrays.toString(announcement.getDishes()));
        announcementDAO.create(tmp);

        tmp = announcementDAO.getByOwnerDate(announcement.getOwnerID().toString(), announcement.getDate())
                .orElseThrow(InvalidAttributeValueException::new);

        for (Dish dish: announcement.getDishes()
             ) {
            System.out.println(dish);
            dish.setAnnouncementID(tmp.getID());
            System.out.println(dish);
            dishDAO.create(dish);
        }

    }

    public void update(Announcement announcement) throws NoSuchElementException {

    }
}
