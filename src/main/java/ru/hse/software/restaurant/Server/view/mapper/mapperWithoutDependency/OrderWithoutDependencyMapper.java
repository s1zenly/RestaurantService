package ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithoutDependency;

@Mapper
public interface OrderWithoutDependencyMapper extends DataMapperWithoutDependency<Order, OrderDTO> {
    OrderWithoutDependencyMapper INSTANCE = Mappers.getMapper(OrderWithoutDependencyMapper.class);

    @Override
    @Named("toEntityWithoutDependency")
    @Mapping(target = "dishes", ignore = true)
    Order toEntityWithoutDependency(OrderDTO orderDTO);

    @Override
    @Named("toDTOWithoutDependency")
    @Mapping(target = "dishes", ignore = true)
    OrderDTO toDTOWithoutDependency(Order order);
}
