package ru.hse.software.restaurant.Server.view.mapper.interfaces;

public interface DataMapperWithDependency<E, D> {
    E toEntity(D dto);
    D toDTO(E entity);
}
