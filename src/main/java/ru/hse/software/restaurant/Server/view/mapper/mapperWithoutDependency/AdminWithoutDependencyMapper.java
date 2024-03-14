package ru.hse.software.restaurant.Server.view.mapper.mapperWithoutDependency;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.hse.software.restaurant.Server.view.dto.AdminDTO;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.mapper.interfaces.DataMapperWithoutDependency;

@Mapper
public interface AdminWithoutDependencyMapper extends DataMapperWithoutDependency<Admin, AdminDTO> {

    AdminWithoutDependencyMapper INSTANCE = Mappers.getMapper(AdminWithoutDependencyMapper.class);

    @Override
    @Named("toEntityWithoutDependency")
    Admin toEntityWithoutDependency(AdminDTO adminDTO);

    @Override
    @Named("toDTOWithoutDependency")
    AdminDTO toDTOWithoutDependency(Admin admin);
}
