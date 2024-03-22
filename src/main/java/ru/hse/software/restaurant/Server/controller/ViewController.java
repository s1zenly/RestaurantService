package ru.hse.software.restaurant.Server.controller;


import ru.hse.software.restaurant.Server.service.ViewService;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewController {

    private final ViewService viewService = new ViewService();

    public List<DishDTO> getAllDish() throws SQLException {
        List<Dish> dishes = viewService.getAllDish();

        if(dishes == null) {
            return null;
        }
        return dishes.stream()
                .map(DishMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
