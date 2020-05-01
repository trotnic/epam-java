package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.beans.Dish;
import com.takeandfood.takeandfood.dto.DishDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class DishMapper extends AbstractMapper<Dish, DishDto> {

    private final ModelMapper modelMapper;
    private final AnnouncementDao announcementDAO;

    @Autowired
    public DishMapper(ModelMapper modelMapper, AnnouncementDao announcementDAO) {
        super(Dish.class, DishDto.class);
        this.modelMapper = modelMapper;
        this.announcementDAO = announcementDAO;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Dish.class, DishDto.class)
                .addMappings(m -> m.skip(DishDto::setAnnouncementId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(DishDto.class, Dish.class)
                .addMappings(m -> m.skip(Dish::setAnnouncement)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Dish entitySource, DishDto dtoDestination) {
        dtoDestination.setAnnouncementId(getId(entitySource));
    }

    private Long getId(Dish source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAnnouncement().getId();
    }

    @Override
    void dtoToEntitySpecificFields(DishDto dtoSource, Dish entityDestination) {
        entityDestination.setAnnouncement(announcementDAO.get(dtoSource.getAnnouncementId().toString()).orElse(null));
    }
}
