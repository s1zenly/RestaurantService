package ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithDependency;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency.OrderWithoutDependencyMapper;

@Mapper(uses = OrderWithoutDependencyMapper.class)
public interface DishMapper extends DataMapperWithDependency<Dish, DishDTO> {

    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);


    @Override
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toEntityWithoutDependency")
    Dish toEntity(DishDTO dto);

    @Override
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toDTOWithoutDependency")
    DishDTO toDTO(Dish entity);
}
