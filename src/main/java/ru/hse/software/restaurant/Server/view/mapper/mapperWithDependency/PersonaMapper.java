package ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.AdminDTO;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithDependency;

@Mapper
public abstract class PersonaMapper {
    public static PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    public abstract User toEntity(UserDTO userDTO);

    public abstract Admin toEntity(AdminDTO adminDTO);

    public abstract UserDTO toDTO(User user);

    public abstract AdminDTO toDTO(Admin admin);
}