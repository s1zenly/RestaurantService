package ru.hse.software.restaurant.Server.service;

import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.repository.AdminRepository;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;
import ru.hse.software.restaurant.Server.view.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class ViewService {
    private final DishRepository dishRepository = new DishRepository();

    public List<Dish> getAllDish() throws SQLException {
        return dishRepository.getAllDish();
    }


}
