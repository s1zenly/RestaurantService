package ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithDependency;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency.DishWithoutDependencyMapper;

@Mapper(uses = DishWithoutDependencyMapper.class)
public interface OrderMapper extends DataMapperWithDependency<Order, OrderDTO> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Override
    @Mapping(target = "dishes", source = "dishes", qualifiedByName = "toEntityWithoutDependency")
    Order toEntity(OrderDTO dto);

    @Override
    @Mapping(target = "dishes", source = "dishes", qualifiedByName = "toDTOWithoutDependency")
    OrderDTO toDTO(Order entity);
}
