package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.AnnouncementDao;
import com.takeandfood.takeandfood.beans.Dish;
import com.takeandfood.takeandfood.dto.DishDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DishMapper extends AbstractMapper<Dish, DishDto> {

    private final ModelMapper modelMapper;
    private final AnnouncementDao announcementDao;

    @Autowired
    public DishMapper(ModelMapper modelMapper, AnnouncementDao announcementDao) {
        super(Dish.class, DishDto.class);
        this.modelMapper = modelMapper;
        this.announcementDao = announcementDao;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Dish.class, DishDto.class)
                .addMappings(m -> m.skip(DishDto::setAnnouncementId)).setPostConverter(toDtoConverter());

/*
    Have no ideas about why this thing doesn't work when i'm trying to persist parent with child in oneToMany relation at once
    Maybe think about later
*/
//        modelMapper.createTypeMap(DishDto.class, Dish.class)
//                .addMappings(m -> m.skip(Dish::setAnnouncement)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Dish entitySource, DishDto dtoDestination) {
        dtoDestination.setAnnouncementId(getId(entitySource));
    }

    private Long getId(Dish source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAnnouncement().getId();
    }

//    @Override
//    void dtoToEntitySpecificFields(DishDto dtoSource, Dish entityDestination) {
//        entityDestination.setAnnouncement(announcementDao.get(dtoSource.getAnnouncementId()).orElse(null));
//    }
}
