package ru.hse.software.restaurant.Server.view.mapper.interfaces;

public interface DataMapperWithoutDependency<E, D> {
    E toEntityWithoutDependency(D dto);
    D toDTOWithoutDependency(E entity);
}
