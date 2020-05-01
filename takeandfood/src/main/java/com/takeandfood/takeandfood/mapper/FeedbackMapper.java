package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.PersonDao;
import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.beans.Feedback;
import com.takeandfood.takeandfood.dto.FeedbackDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class FeedbackMapper extends AbstractMapper<Feedback, FeedbackDto> {

    private final ModelMapper modelMapper;
    private final RestaurantDao restaurantDAO;
    private final PersonDao personDAO;

    @Autowired
    FeedbackMapper(ModelMapper modelMapper, RestaurantDao restaurantDAO, PersonDao personDAO) {
        super(Feedback.class, FeedbackDto.class);
        this.modelMapper = modelMapper;
        this.restaurantDAO = restaurantDAO;
        this.personDAO = personDAO;
    }


    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Feedback.class, FeedbackDto.class)
                .addMappings(m -> m.skip(FeedbackDto::setRestaurantId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FeedbackDto::setPersonId)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(FeedbackDto.class, Feedback.class)
                .addMappings(m -> m.skip(Feedback::setRestaurant)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Feedback::setPerson)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Feedback entitySource, FeedbackDto dtoDestination) {
        dtoDestination.setRestaurantId(getRestaurantId(entitySource));
        dtoDestination.setPersonId(getPersonId(entitySource));
    }

    private Long getRestaurantId(Feedback source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getRestaurant().getId();
    }

    private Long getPersonId(Feedback source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getPerson().getId();
    }

    @Override
    void dtoToEntitySpecificFields(FeedbackDto dtoSource, Feedback entityDestination) {
        entityDestination.setRestaurant(restaurantDAO.get(dtoSource.getRestaurantId()).orElse(null));
        entityDestination.setPerson(personDAO.get(dtoSource.getPersonId()).orElse(null));
    }
}

