package ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithoutDependency;

@Mapper
public interface UserWithoutDependencyMapper extends DataMapperWithoutDependency<User, UserDTO> {

    UserWithoutDependencyMapper INSTANCE = Mappers.getMapper(UserWithoutDependencyMapper.class);

    @Override
    @Named("toEntityWithoutDependency")
    User toEntityWithoutDependency(UserDTO userDTO);

    @Override
    @Named("toDTOWithoutDependency")
    UserDTO toDTOWithoutDependency(User user);
}
