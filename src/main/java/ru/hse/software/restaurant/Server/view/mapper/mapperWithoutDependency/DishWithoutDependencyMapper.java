package ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithoutDependency;

@Mapper
public interface DishWithoutDependencyMapper extends DataMapperWithoutDependency<Dish, DishDTO> {
    DishWithoutDependencyMapper INSTANCE = Mappers.getMapper(DishWithoutDependencyMapper.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "orders", ignore = true)
    Dish toEntityWithoutDependency(DishDTO dishDTO);

    @Override
    @Named("toDTOWithoutDependency")
    @Mapping(target = "orders", ignore = true)
    DishDTO toDTOWithoutDependency(Dish dish);
}
