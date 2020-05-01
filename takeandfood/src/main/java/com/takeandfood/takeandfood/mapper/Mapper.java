package com.takeandfood.takeandfood.mapper;/*
 * @project takeandfood
 * @author vladislav on 5/1/20
 */

public interface Mapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
