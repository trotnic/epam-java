package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.dto.AnnouncementDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;


@Component
public class AnnouncementMapper extends AbstractMapper<Announcement, AnnouncementDto> {

    private final ModelMapper modelMapper;
    private final RestaurantDao restaurantDAO;


    @Autowired
    AnnouncementMapper(ModelMapper modelMapper, RestaurantDao restaurantDAO) {
        super(Announcement.class, AnnouncementDto.class);
        this.modelMapper = modelMapper;
        this.restaurantDAO = restaurantDAO;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Announcement.class, AnnouncementDto.class)
                .addMappings(m -> m.skip(AnnouncementDto::setRestaurantId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(AnnouncementDto::setDate)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(AnnouncementDto.class,Announcement.class)
                .addMappings(m -> m.skip(Announcement::setRestaurant)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Announcement entitySource, AnnouncementDto dtoDestination) {
        dtoDestination.setRestaurantId(getRestaurantId(entitySource));
        dtoDestination.setDate(getStringDate(entitySource));
    }

    private Long getRestaurantId(Announcement source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getRestaurant().getId();
    }

    private String getStringDate(Announcement source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(source.getDate());
        return date;
    }

    @Override
    void dtoToEntitySpecificFields(AnnouncementDto dtoSource, Announcement entityDestination) {
        entityDestination.setRestaurant(restaurantDAO.get(dtoSource.getRestaurantId()).orElse(null));
        entityDestination.setDate(getDate(dtoSource));
    }

    private Date getDate(AnnouncementDto source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(source.getDate());
        } catch (ParseException e) {
            return new Date();
        }

    }
}
