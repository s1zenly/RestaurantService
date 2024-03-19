package ru.hse.software.restaurant.Server.service;


import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;

import java.sql.SQLException;

public class AdminService {
    private final DishRepository dishRepository = new DishRepository();

    public boolean addDishInMenu(DishDTO dishDTO) throws SQLException {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);

        if(dishRepository.findByTittle(dish.getTitle()) != null) {
            return false;
        }

        dishRepository.save(dish);
        return true;
    }

    public boolean deleteDishInMenu(String dishTitle) throws SQLException {
        if(dishRepository.findByTittle(dishTitle) == null) {
            return false;
        }

        dishRepository.delete(dishTitle);
        return true;
    }

    public boolean changeDishInMenu(String dishTitle, DishDTO dishDTO) throws SQLException {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);

        if(dishRepository.findByTittle(dishTitle) == null) {
            return false;
        }

        dishRepository.update(dishTitle, dish);
        return true;
    }
}
