package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dto.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    private ModelMapper modelMapper;
    private RestaurantDao restaurantDao;

    @Autowired
    public PersonMapper(ModelMapper modelMapper, RestaurantDao restaurantDao) {
        super(Person.class, PersonDto.class);
        this.modelMapper = modelMapper;
        this.restaurantDao = restaurantDao;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Person.class, PersonDto.class)
                .addMappings(m -> m.skip(PersonDto::setRestaurantId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(PersonDto::setStatus)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(PersonDto::setRole)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(PersonDto.class, Person.class)
                .addMappings(m -> m.skip(Person::setRestaurant)).setPostConverter(toEntityConverter());
    }

    @Override
    void entityToDtoSpecificFields(Person entitySource, PersonDto dtoDestination) {
        dtoDestination.setRestaurantId(getRestaurantId(entitySource));
        dtoDestination.setRole(getRole(entitySource));
        dtoDestination.setStatus(getStatus(entitySource));
    }

    private Long getRole(Person source) {
        return Objects.isNull(source) ||
                Objects.isNull(source.getId()) ||
                Objects.isNull(source.getRole())? null :
                (long) source.getRole();
    }

    private Long getStatus(Person source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : (long) source.getStatus();
    }

    private Long getRestaurantId(Person source) {
        return Objects.isNull(source) || Objects.isNull(source.getRestaurant()) ? null : source.getRestaurant().getId();
    }

    @Override
    void dtoToEntitySpecificFields(PersonDto dtoSource, Person entityDestination) {
        if (dtoSource.getRestaurantId() != null) {
            entityDestination.setRestaurant(restaurantDao.get(dtoSource.getRestaurantId()).orElse(null));
        } else {
            entityDestination.setRestaurant(null);
        }
    }
}
