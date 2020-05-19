package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/16/20
 */


import com.takeandfood.takeandfood.beans.Announcement;
import com.takeandfood.takeandfood.beans.Order;
import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.dao.OrderDao;
import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.dto.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto>{

    private ModelMapper modelMapper;
    private OrderDao orderDao;
    private PersonDao personDao;
    private RestaurantDao restaurantDao;
    private AnnouncementDao announcementDao;

    @Autowired
    public OrderMapper(
            ModelMapper modelMapper,
            OrderDao orderDao,
            RestaurantDao restaurantDao,
            AnnouncementDao announcementDao,
            PersonDao personDao) {
        super(Order.class, OrderDto.class);
        this.modelMapper = modelMapper;
        this.orderDao = orderDao;
        this.personDao = personDao;
        this.announcementDao = announcementDao;
        this.restaurantDao = restaurantDao;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setAnnouncementId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setRestaurantId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDto::setUserId)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(OrderDto.class, Order.class)
                .addMappings(m -> m.skip(Order::setAnnouncement)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setPerson)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Order::setRestaurant)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Order entitySource, OrderDto dtoDestination) {
        dtoDestination.setRestaurantId(getRestaurantId(entitySource));
        dtoDestination.setAnnouncementId(getAnnouncementId(entitySource));
        dtoDestination.setUserId(getPersonId(entitySource));
    }

    private Long getAnnouncementId(Order source) {
        return Objects.isNull(source) || Objects.isNull(source.getRestaurant()) ? null : source.getAnnouncement().getId();
    }

    private Long getRestaurantId(Order source) {
        return Objects.isNull(source) || Objects.isNull(source.getRestaurant()) ? null : source.getRestaurant().getId();
    }

    private Long getPersonId(Order source) {
        return Objects.isNull(source) || Objects.isNull(source.getRestaurant()) ? null : source.getPerson().getId();
    }

    @Override
    void dtoToEntitySpecificFields(OrderDto dtoSource, Order entityDestination) {
        entityDestination.setAnnouncement(announcementDao.get(dtoSource.getAnnouncementId()).orElse(null));
        entityDestination.setRestaurant(restaurantDao.get(dtoSource.getRestaurantId()).orElse(null));
        entityDestination.setPerson(personDao.get(dtoSource.getUserId()).orElse(null));
    }
}
