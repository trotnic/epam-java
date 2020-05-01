package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

import com.takeandfood.takeandfood.dao.RestaurantDao;
import com.takeandfood.takeandfood.beans.Person;
import com.takeandfood.takeandfood.dto.PersonDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    private final ModelMapper modelMapper;
    private final RestaurantDao restaurantDAO;

    @Autowired
    public PersonMapper(ModelMapper modelMapper, RestaurantDao restaurantDAO) {
        super(Person.class, PersonDto.class);
        this.modelMapper = modelMapper;
        this.restaurantDAO = restaurantDAO;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Person.class, PersonDto.class)
                .addMappings(m -> m.skip(PersonDto::setRestaurantId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(PersonDto.class, Person.class)
                .addMappings(m -> m.skip(Person::setRestaurant));
    }

    @Override
    void entityToDtoSpecificFields(Person entitySource, PersonDto dtoDestination) {
        dtoDestination.setRestaurantId(getRestaurantId(entitySource));
    }

    private Long getRestaurantId(Person source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getRestaurant().getId();
    }

    @Override
    void dtoToEntitySpecificFields(PersonDto dtoSource, Person entityDestination) {
        entityDestination.setRestaurant(restaurantDAO.get(dtoSource.getRestaurantId().toString()).orElse(null));
    }
}
